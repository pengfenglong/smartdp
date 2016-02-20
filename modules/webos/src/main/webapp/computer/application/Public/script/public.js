/*
 * public.js
 * name:xiaojia
 * email:iatt@qq.com
 * qq:273142650
 * time:2012.6.15 11:20
*/

jAdmin = {

	url: {

		get: function (name, type) {

			var url, left;

			url = location.href;

			url = url.replace(/(.*\?)|(\#.*)/ig, '').split('&');

			for (i = 0; i < url.length; i++) {

				left = url[i].replace(/[=](\s|\S)*/ig, '');

				if (left == name) {

					if (type == 'all') {

						return url[i];

					} else {

						return url[i].replace(/(\s|\S)*[^=]*=/ig, '');

					}

				}

			}

			return null;

		}

	},

	oper: {

		Submit: function (obj, src, type, json) {

			var obj = jQuery(obj);

			var data = this.getFormInput(obj.parents('form'));

			var _this = jAdmin;

			if (typeof json == 'undefined') {

				json = Object;

			}

			jQuery.ajax({

				type: 'POST',

				data: data,

				url: src,

				beforeSend: function () {

					obj.attr('disabled', true);

					obj.attr('class', 'submit-ash');

				},

				success: function () {

					top.jQuery.jLayer.alert({

						title: '提示',

						subject: '提交成功',

						sure: function () {

							var articleLayer = top.jQuery('#application_article iframe');

							switch (type) {

								case 'upload':

									top.jQuery.jLayer.close('#' + _this.url.get('layerID'));

									break;

								case 'insert':

									obj.attr('disabled', false);

									obj.attr('class', 'submit');

									break;

							}

							if (typeof json.sure != 'undefined') {

								json.sure();

							}

							if (articleLayer.size() > 0) {

								articleLayer.attr('src', articleLayer.attr('src'));

							}

						}

					});

				}

			});

		},

		getLayer: function (json) {

			top.jQuery.jLayer.layer({

				title: json.title,

				id: json.id,

				MenuData: {

					name: json.title,

					icon: 'resources/images/icon/application.png'

				},

				close: function () {
					
					top.jPushApplication.menu();
				
				},

				width: json.width,

				height: json.height,

				subject: '<iframe frameborder="0" width="100%" height="100%" src="' + json.src + '&layerID=' + json.id + '"></iframe>'

			});

			top.jPushApplication.menu(json.id);

		},

		Upload: function (title, src, json) {

			var id = 'jAdminLayerUpload_' + parseInt(Math.random() * (1000 + 9999));

			var title = title + '-修改';

			if (typeof json == 'undefined') {

				json = {

					width: '900px',

					height: '500px'

				}

			}

			this.getLayer({
			
				title: title,

				id: id,

				width: json.width,

				height: json.height,

				src: src

			});

		},

		Insert: function (title, src, json) {

			var id = 'jAdminLayerInsert_' + parseInt(Math.random() * (1000 + 9999));

			var title = title + '-添加';

			if (typeof json == 'undefined') {

				json = {

					width: '900px',

					height: '500px'

				}

			}

			this.getLayer({
			
				title: title,

				id: id,

				width: json.width,

				height: json.height,

				src: src

			});

		},

		Delete: function (title, src, obj, json) {

			var obj = jQuery(obj);

			if (typeof json == 'undefine') {

				json = Object;

			}

			top.jQuery.jLayer.confirm({

				subject: '确认删除“' + title + '”吗？',

				sure: function () {

					jQuery.ajax({

						type: 'GET',

						url: src,

						beforeSend: function () {

							obj.addClass('table-list-oper-d-wait');

						},

						success: function () {

							if (typeof json.sure != 'undefined') {

								json.sure();

							} else {

								obj.parents('tr').remove();

							}

						}

					});

				}

			});

		},

		getFormInput: function (form) {

			var data = '';

			var array = [];

			var name, value;

			form.find('input[type!=hidden],select,textarea:[class!=jEdit]').each(

				function () {

					name = $(this).attr('name');

					value = $(this).attr('value');

					if (typeof name != 'undefined') {

						if (!$(this).hasClass('jEdit')) {

							array.push(name + '=' + escape(value));

						} else {

							array.push(name + '=' + escape(jPlugin.data.jEdit.html()));

						}

					}

				}

			);

			form.find('img').each(

				function () {

					name = $(this).attr('name');

					value = $(this).attr('src');

					array.push(name + '=' + escape(value));

				}

			);

			data = array.join('&');

			return data;

		}

	}

}