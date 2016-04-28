/**
 * 后台专用script , for easyui
 */
function loadError() {
	alert("加载数据出错！");
}

function beforeLoad(){
	if($(this).data("firstLoad") == undefined){
		$(this).data("firstLoad",false);
		var autoLoad = true;
		
		if($(this).datagrid("options").autoLoad != undefined){
			autoLoad = $(this).datagrid("options").autoLoad;
		}
		return autoLoad;
	}
	return true;
}

function DataGridExt(context,index,templateUrl){
	this.context = context;
	this.index = index;
	this.templateUrl = templateUrl;
	this.crud = 'all';//'c','r','u','d','all'
	this.saving = false; //处理中
}

$.extend(DataGridExt.prototype,{
	init: function(config){
		if(config){
			$.extend(this,config);
		}

		this.dataGrid = $("#dg-"+this.index);
		this.toolBar = $("#toolbar-"+this.index);
		this.dlg = $("#dlg-"+this.index);
		this.dlgBtn = $("#dlg-buttons-"+this.index);
		
		var fns = [this.proxy(this.add,this,this.toolBar.find(".add")),
		           this.proxy(this.edit,this,this.toolBar.find(".edit")),
		           this.proxy(this.remove,this,this.toolBar.find(".remove"))];
		
		if(this.crud == 'all'){
            this.toolBar.find(".add").bind('click',fns[0]);
            this.toolBar.find(".edit").bind("click",fns[1]);
            this.toolBar.find(".remove").bind("click",fns[2]);
		}else if(this.crud=='c'){
            this.toolBar.find(".add").bind('click',fns[0]);
        }else if(this.crud=='u'){
            this.toolBar.find(".edit").bind('click',fns[1]);
        }else if(this.crud=='d'){
            this.toolBar.find(".remove").bind('click',fns[2]);
        }

        if(this.crud=='all' || this.crud == 'c' || this.crud == 'u'){
            this.dlgBtn.find('.save').bind('click',this.proxy(this.save,this,this.dlgBtn.find('.save')));
            this.dlgBtn.find('.cancel').bind('click',this.proxy(this.cancel,this,this.dlgBtn.find('.cancel')));
        }
	},
	
	resetForm:function(form){
        var form = $(form);
        form[0].reset();
        form.find("[type=hidden]").val("");
    },

    proxy: function(fn,scope,btn){
        return function(){
            if(btn.is("[class*='disabled']")){//禁用了不需要响应事件
                return;
            }
            return fn.call(scope,arguments[0],btn);
        };
    },
    formInit:function(){
    	var pidTxt = this.dlg.find("form:eq(0) input[type=hidden][name='pid']");
    	if(!pidTxt.val()){
    		pidTxt.val('-1');
    	}
    	
    },
    add: function(){
        $('#dlg-'+this.index).dialog('open').dialog('setTitle','新增');
        this.resetForm(this.dlg.find("form:eq(0)"));
        this.formInit.call(this);
    },
    formLoadData:function(data){
        //处理隐藏域
        this.dlg.find("form:eq(0) input[type!=radio][type!=checkbox][name*='.']").each(function(){
            var name = $(this)[0].name;
            var value = data[name];
            if(value){
                $(this).val(value);
                return;
            }

            if(name.indexOf(".")!=-1){
                var names = name.split(".");
                value = data;
                for(var i= 0,l=names.length;i<l;i++){
                    value = value[names[i]];
                    if(!value){
                        return;
                    }
                }
                $(this).val(value);
            }
        });
        //处理单选多选
        this.dlg.find("form:eq(0) input[type=checkbox]").each(function(){
            var name = $(this)[0].name;
            var value = data[name];
            var this_ = this;
            if(value){
                $(value).each(function(index,item){
                    if($(this_).val()==item){
                        $(this_)[0].checked = "checked";
                    }
                });
                return ;
            }

            if(name.indexOf(".")!=-1){
                var names = name.split(".");
                value = data;
                for(var i= 0,l=names.length-1;i<l;i++){
                    try{
                        value = value[names[i]];
                    }catch(e){
                        return;
                    }
                }

                if($.isArray(value)){
                    for(var i= 0,l=value.length;i<l;i++){
                        if(value[i][names[names.length-1]]==$(this).val()){
                            $(this)[0].checked = "checked";
                            return;
                        }
                    }
                }
            }else{
                value = data[name];
            }
            if($(this).val() == value){
                $(this)[0].checked = "checked";
            }
        });

        this.dlg.find("form:eq(0) select").each(function(){
            var name = $(this)[0].name;
            var value = data[name];

            if(value){
                $(this).val(value);
                return;
            }

            if(name.indexOf(".")!=-1){
                var names = name.split(".");
                value = data;
                for(var i= 0,l=names.length;i<l;i++){
                    value = value[names[i]];
                    if(!value){
                        return;
                    }
                }
            }else{
                value = data[name];
            }

            $(this).val(value);
        });
    },
    edit: function(){
        var rows = this.dataGrid.datagrid('getSelections');
        if(!rows || rows.length == 0 ){
            $.messager.alert('提示','请选择记录!');
        }else{
            if(rows.length == 1){
                this.dlg.dialog('open').dialog('setTitle','修改');
                this.dlg.find('form').form('clear').form('load',rows[0]);
                this.formLoadData(rows[0]);
            }else{
                $.messager.alert('提示','请选择单行记录!');
            }
        }
    },
    reload:function(){
        this.dataGrid.datagrid('reload');
    },
    remove:function(){
        var this_ = this;
        var rows = $('#dg-'+this.index).datagrid('getSelections');
        if(!rows || rows.length > 0){
            $.messager.confirm('确认','你确定要删除所选的记录吗?',function(r){
                    if(r){
                        $.post(urlDoDelete,
                            {
                                pids: $.map(rows,function(row){
                                    return row.pid;
                                }).join("::")
                            },function(result){
                                if(result.status){
                                    this_.reload.call(this_);
                                    $.messager.show({
                                        title:'提示',
                                        msg:result.message
                                    });
                                }
                            },
                            'json'
                        );
                    }
            });
        }else{
            $.messager.alert('提示','请选择记录!');
        }
    },
    validateForm:function(form){
        return true;
    },
    save: function(){
        if(this.saving==true){//避免重复提交
            return;
        }
        var this_ = this;
        var form = this.dlg.find('form:eq(0)');
        var url;
        if(form[0].pid.value > 0){
            url = urlDoUpdate;
        }else{
            url = urlDoSave;
        }

        form.form('submit',{
           url:url,
           onSubmit: function(){
               var validate = $(this).form('validate')&&this_.validateForm(form);
               if(validate){
                   this_.saving = true;
               }

               return validate;
           },
            success:function(result){
                this_.saving = false;
                var result;
                try{
                    result = $.parseJSON(result);
                }catch(e){
                    $.messager.alert('错误','服务端出错!'); // show error
                    return;
                }

                if(result.status){
                    this_.dlg.dialog('close');
                    this_.reload.call(this_);
                    $.messager.show({ // show tips
                        title:'提示',
                        msg:result.message
                    });
                }else{
                    $.messager.alert('错误',result.message); //show error
                }
            },
            onLoadError:function(){
                this_.saving = false;
            }
        });
    },
    cancel:function(){
        this.dlg.dialog('close');
    }
});

/**
 * 取属性
 */
function getProperty(name){
    return function(value,row,index){
        if(!value){
            return "";
        }
        return value[name];
    };
}

/**
 * 递归取值
 */
function findNodeInTree(src,propertyName,propertyValue){
    if($.isArray(src)){
        for(var i= 0,l=src.length;i<l;i++){
            var value = findNodeInTree(src[i],propertyName,propertyValue);

            if(value){
                return value;
            }
        }
    }else{
        if(!src){
            return null;
        }

        if(src[propertyName]==propertyValue){
            return src;
        }else{
            if(src.children){
                return findNodeInTree(src.children,propertyName,propertyValue);
            }else{
                return null;
            }
        }
    }
}