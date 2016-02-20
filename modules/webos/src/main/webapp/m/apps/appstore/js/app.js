$(function () {
	pframe.util.importTpl('apps/appstore/tpl/app.tpl');

	var model = pframe.create('Model', {
			init : function () {
				var me = this;
				$.getJSON('apps/appstore/data/app.json', function (data) {
					me.trigger('list', data);
				});
			}
		});

	var view = pframe.create('View', {
			init : function () {
				this.listenTo(model, 'list', this.list);
				this.listenTo(model, 'add', this.add);
				this.listenTo(model, 'remove', this.remove);
			},
			list : function (data) {
				//本地存储中我的apps
				var myapps = pframe.util.store('store-myapps');

				for (var i = 0, len = data.apps.length; i < len; i++) {
					if (myapps.indexOf(data.apps[i].code) == -1) {
						data.apps[i].has = false;
					} else {
						data.apps[i].has = true;
					}
				}
				var template = Handlebars.compile(pframe.util.getTpl("appstore-template"));
				$('#appstore-container').html(template(data));

				window.scrollTo(0, 0);

				function addEvent() {
					var code = $(this).attr('data-code');
					if (myapps.indexOf(code) == -1) {
						myapps.push(code);
						pframe.util.store('store-myapps', myapps);
						$(this).removeClass('btn-green');
						$(this).addClass('btn-gray');
						$(this).text('删除');
						$(this).unbind('click', addEvent);
						$(this).on('click', delEvent);
					}
				}

				function delEvent() {
					var code = $(this).attr('data-code');
					if (myapps.indexOf(code) != -1) {
						myapps.splice(myapps.indexOf(code), 1);
						pframe.util.store('store-myapps', myapps);
						$(this).removeClass('btn-gray');
						$(this).addClass('btn-green');
						$(this).text('添加');
						$(this).unbind('click', delEvent);
						$(this).on('click', addEvent);
					}
				}

				$('#appstore-container .btn-green').on('click', addEvent);

				$('#appstore-container .btn-gray').on('click', delEvent);

			},
			add : function () {},
			remove : function () {}
		});

});