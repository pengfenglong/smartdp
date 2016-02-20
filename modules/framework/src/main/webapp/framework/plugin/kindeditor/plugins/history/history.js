KindEditor.plugin('history', function(K) {
    var editor = this, name = 'history';
    // 点击图标时执行
    editor.clickToolbar(name, function() {
            showHistoryMessage(editor);
    });
});
KindEditor.lang({
	history : '历史消息'
});
