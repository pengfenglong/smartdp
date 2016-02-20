/**
 * web数据库工具类
 * @author pengfenglong
 */
var WebdatabaseUtil = {
		db : null,
		init : function(){
			db = openDatabase("pengfenglong", "1.0", "this is pengfenglong web database", 360 * 1024 * 1024);
		},
		/**
		 * sql:CREATE TABLE IF NOT EXISTS MyDatabase3(name TEXT,message TEXT,time INTEGER)
		 */
		createTable : function(sql){
			db.transaction(function(tx) {
				// 如果表不存在的话，就生成数据表
				tx.executeSql(
					sql,
					[], 
					function(tx, rs) {
					}, 
					function(tx, error) {
						//alert(error.source + "::" + error.message);
					}
				);
			});
		},
		/**
		 * sql:INSERT INTO MyDatabase3 VALUES(?,?,?)
		 * data:['a','b','c']
		 */
		save : function(sql,data){
			db.transaction(function(tx) {
				// 插入一条信息记录
				tx.executeSql(
					sql, 
					data, 
					function(tx, rs) {
					}, 
					function(tx, error) {
						alert(error.source + "::" + error.message);
					}
				);
			});
		},
		/**
		 * sql:SELECT * FROM MyDatabase3'
		 */
		query : function(sql,callback){
			db.transaction(function(tx) {
				tx.executeSql(
					sql, 
					[],
					function(tx, rs) {
						var data = [];
						for(var i=0;i<rs.rows.length;i++){
							data.push(rs.rows.item(i));
						}
						callback(data);
					}, 
					function(tx, error) {
						callback([]);
					}
				);
			});
		}
}
//WebdatabaseUtil.init();