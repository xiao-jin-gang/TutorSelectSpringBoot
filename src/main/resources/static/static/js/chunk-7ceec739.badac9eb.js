(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-7ceec739"],{"42c7":function(e,t,n){"use strict";n.r(t);var r=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"app-container",staticStyle:{"margin-top":"8%"}},[n("el-row",[n("el-col",{attrs:{span:6,offset:2}},[n("div",{staticClass:"grid-content bg-purple"},[n("span",{staticClass:"demonstration",staticStyle:{"font-size":"16px"}},[e._v("设置系统提交申请的时间范围 ")])])]),n("el-col",{attrs:{span:8}},[n("div",{staticClass:"grid-content bg-purple-light"},[n("div",{staticClass:"block"},[n("el-date-picker",{attrs:{type:"daterange","value-format":"yyyy-MM-dd",align:"right","unlink-panels":"","range-separator":"至","start-placeholder":"开始日期","end-placeholder":"结束日期"},model:{value:e.time,callback:function(t){e.time=t},expression:"time"}})],1)])]),n("el-col",{attrs:{span:8}},[n("div",{staticClass:"grid-content bg-purple"},[n("el-button",{attrs:{type:"primary",plain:""},on:{click:function(t){return e.save()}}},[e._v("保存设置")])],1)])],1)],1)},a=[],s=n("1da1"),i=(n("96cf"),{data:function(){return{time:""}},created:function(){this.getTime()},methods:{save:function(){var e=this;return Object(s["a"])(regeneratorRuntime.mark((function t(){var n,r,a;return regeneratorRuntime.wrap((function(t){while(1)switch(t.prev=t.next){case 0:if(n=0,""!==e.time){t.next=3;break}return t.abrupt("return",e.$message("请选择设置的开始时间与结束时间"));case 3:return t.next=5,e.$http.get("/admin/system-time/save/"+e.time+"/"+n);case 5:if(r=t.sent,a=r.code,"20000"!=a){t.next=11;break}return t.abrupt("return",e.$message.success("时间更新成功"));case 11:if("20002"!=a){t.next=15;break}return t.abrupt("return",e.$message.success("时间设置成功"));case 15:return t.abrupt("return",e.$message.error("时间修改失败"));case 16:case"end":return t.stop()}}),t)})))()},getTime:function(){var e=this;return Object(s["a"])(regeneratorRuntime.mark((function t(){var n,r,a;return regeneratorRuntime.wrap((function(t){while(1)switch(t.prev=t.next){case 0:return n=0,t.next=3,e.$http.get("/admin/system-time/get/"+n);case 3:r=t.sent,a=r.data,console.info(a),e.time=[],e.time.push(a[0]),e.time.push(a[1]);case 9:case"end":return t.stop()}}),t)})))()}}}),c=i,u=(n("54cc"),n("2877")),o=Object(u["a"])(c,r,a,!1,null,null,null);t["default"]=o.exports},"54cc":function(e,t,n){"use strict";n("f42e")},f42e:function(e,t,n){}}]);