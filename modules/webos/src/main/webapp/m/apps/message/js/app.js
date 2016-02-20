$(function() {
	pframe.util.importTpl('apps/message/tpl/app.tpl');
	pframe.define('app.message.view', {
		init: function() {
			var me = this;
			$.getJSON('apps/message/data/app.json', function(data) {

				var template = Handlebars.compile(pframe.util.getTpl("message-template"));
				$('#message-container').html(template(data));
				window.scrollTo(0,0);
			});
		}
	});
	pframe.create('app.message.view');

});