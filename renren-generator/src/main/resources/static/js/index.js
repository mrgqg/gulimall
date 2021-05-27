//iframe自适应
$(window).on('resize', function() {
	var $content = $('.content');
	$content.height($(this).height() - 120);
	$content.find('iframe').each(function() {
		$(this).height($content.height());
	});
}).resize();


var vm = new Vue({
	el:'#rrapp',
	data:{
		main:"main.html",
        navTitle:"欢迎页",
		formData:{
			jdbcUrl:'',
			username:'',
			password:'',
			mainPath:'',
			author:'',
			packageName:'',
			email:'',
			tablePrefix:'',
			moduleName:''

		}
	},
    methods: {
        donate: function () {
            layer.open({
                type: 2,
                title: false,
                area: ['806px', '467px'],
                closeBtn: 1,
                shadeClose: false,
                content: ['http://cdn.renren.io/donate.jpg', 'no']
            });
        },
		datasource:function (){
			layer.open({
				type: 1,
				title: "数据源",
				area: ['806px', '550px'],
				closeBtn: 1,
				btn:["确认"],
				content: $("#dataForm"),
				success:function (index,layero){
					$.ajax({
						type: "GET",
						url: "/sys/generator/getDatasource",
						success: function(data){
							vm.formData=data.data
						}
					});
				},
				yes:function (){
					console.log(JSON.stringify(vm.formData))
					$.ajax({
						type: "post",
						url: "/sys/generator/setDatasource",
						data: JSON.stringify(vm.formData),
						dataType:"json",
						contentType:"application/json",
						success: function(data){
							if(data.code==0){
								layer.msg(data.msg,{icon:1,time:1500})
								setTimeout(function (){
									layer.closeAll();
									location.reload();
								},1500)
							}
						}
					});
				}
			});
		}
    }
});

//路由
var router = new Router();
var menus = ["main.html","generator.html"];
routerList(router, menus);
router.start();

function routerList(router, menus){
	for(var index in menus){
		router.add('#'+menus[index], function() {
			var url = window.location.hash;

			//替换iframe的url
			vm.main = url.replace('#', '');

			//导航菜单展开
			$(".treeview-menu li").removeClass("active");
			$("a[href='"+url+"']").parents("li").addClass("active");

			vm.navTitle = $("a[href='"+url+"']").text();
		});
	}
}
