(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-13ae58e2"],{"0d3b":function(t,e,a){var n=a("d039"),r=a("b622"),i=a("c430"),s=r("iterator");t.exports=!n((function(){var t=new URL("b?a=1&b=2&c=3","http://a"),e=t.searchParams,a="";return t.pathname="c%20d",e.forEach((function(t,n){e["delete"]("b"),a+=n+t})),i&&!t.toJSON||!e.sort||"http://a/c%20d?a=1&c=3"!==t.href||"3"!==e.get("c")||"a=1"!==String(new URLSearchParams("?a=1"))||!e[s]||"a"!==new URL("https://a@b").username||"b"!==new URLSearchParams(new URLSearchParams("a=b")).get("a")||"xn--e1aybc"!==new URL("http://тест").host||"#%D0%B1"!==new URL("http://a#б").hash||"a1c3"!==a||"x"!==new URL("http://x",void 0).host}))},2189:function(t,e,a){"use strict";a.d(e,"b",(function(){return i})),a.d(e,"c",(function(){return s})),a.d(e,"a",(function(){return o})),a.d(e,"e",(function(){return l})),a.d(e,"f",(function(){return u})),a.d(e,"g",(function(){return c})),a.d(e,"d",(function(){return p}));a("d3b7"),a("3ca3"),a("ddb0"),a("9861"),a("99af");var n=a("b775"),r=a("99b1");function i(t,e,a){var i=new URLSearchParams;return i.append("organizationId",t),i.append("applyStatuss",e),i.append("pageNumber",a),Object(n["a"])({url:"".concat(r["a"],"/admin/tutor-inspect/getInit"),method:"post",data:i})}function s(t,e){return Object(n["a"])({url:"".concat(r["a"],"/admin/tutor-inspect/search/").concat(e),method:"post",data:t})}function o(){return Object(n["a"])({url:"/admin/apply-type/getApplyType",method:"get"})}function l(t){return Object(n["a"])({url:"/admin/update-status/update",method:"post",data:t})}function u(t){return Object(n["a"])({url:"/admin/update-status/updateSfh",method:"post",data:t})}function c(t){return Object(n["a"])({url:"/admin/update-status/updateXy",method:"post",data:t})}function p(t){return Object(n["a"])({url:"/admin/update-status/updateSocial",method:"post",data:t})}},"42b8":function(t,e,a){"use strict";a("a2a4")},"6fa2":function(t,e,a){"use strict";a.d(e,"d",(function(){return i})),a.d(e,"c",(function(){return s})),a.d(e,"a",(function(){return o})),a.d(e,"b",(function(){return l}));a("99af");var n=a("a78e"),r=a.n(n);function i(t,e,a,n,r){t.$router.push("/applyDetails/".concat(e,"/").concat(a,"/").concat(n,"/").concat(r))}function s(t){var e=new Date(t);return new Date(e.getTime()+e.getTimezoneOffset()/60*3600*1e3)}function o(t){var e=new Date(t);return new Date(e.getTime()+3600*(e.getTimezoneOffset()/60+24)*1e3)}function l(){if(null!==r.a.get("organizationId"))return r.a.get("organizationId");console.log("error-organizationId is null")}},"77a0":function(t,e,a){"use strict";a.r(e);var n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"app-container"},[a("el-form",{ref:"queryForm",attrs:{"label-width":"70px"}},[a("el-row",{attrs:{gutter:20}},[a("el-col",{attrs:{span:6}},[a("el-form-item",{attrs:{label:"工号"}},[a("el-input",{staticStyle:{width:"100%"},attrs:{placeholder:"请输入工号",clearable:"",size:"small"},model:{value:t.queryParams.userId,callback:function(e){t.$set(t.queryParams,"userId",e)},expression:"queryParams.userId"}})],1)],1),a("el-col",{attrs:{span:6}},[a("el-form-item",{attrs:{label:"姓名"}},[a("el-input",{staticStyle:{width:"100%"},attrs:{placeholder:"请输入姓名",clearable:"",size:"small"},model:{value:t.queryParams.userName,callback:function(e){t.$set(t.queryParams,"userName",e)},expression:"queryParams.userName"}})],1)],1),a("el-col",{attrs:{span:6}},[a("el-form-item",{attrs:{label:"申请类别"}},[a("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择",clearable:"",size:"small"},model:{value:t.queryParams.applyType,callback:function(e){t.$set(t.queryParams,"applyType",e)},expression:"queryParams.applyType"}},t._l(t.applyTypeList,(function(t){return a("el-option",{key:t.applyTypeId,attrs:{label:t.applyName,value:t.applyTypeId}})})),1)],1)],1),a("el-col",{attrs:{span:6}},[a("el-col",{attrs:{span:6,offset:6}},[a("el-button",{attrs:{type:"primary",icon:"el-icon-search",size:"small"},on:{click:t.search}},[t._v("搜索")])],1),a("el-col",{attrs:{span:6,offset:2}},[a("el-button",{attrs:{icon:"el-icon-refresh",size:"small"},on:{click:function(e){return t.resetQuery(t.queryParams)}}},[t._v("重置")])],1)],1)],1)],1),a("div",{staticStyle:{margin:"10px 0","border-bottom":"1px solid #dcdfe6","padding-bottom":"10px"}},[a("el-button",{attrs:{type:"danger",plain:"",icon:"el-icon-success",size:"small",disabled:t.single},on:{click:function(e){return t.unPassFun()}}},[t._v("驳回至导师 ")])],1),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.loading,expression:"loading"}],attrs:{data:t.tutorList},on:{"selection-change":t.handleSelectionChange}},[a("el-table-column",{attrs:{type:"selection",align:"center"}}),a("el-table-column",{attrs:{label:"工号",align:"center",prop:"tutorId"}}),a("el-table-column",{attrs:{label:"姓名",align:"center",prop:"name"}}),a("el-table-column",{attrs:{label:"所在单位（院系）",align:"center",prop:"organizationName"}}),a("el-table-column",{attrs:{label:"申请学科或类别代码",align:"center",prop:"applySubject"}}),a("el-table-column",{attrs:{label:"申请类别",align:"center",prop:"applyName"}}),a("el-table-column",{attrs:{label:"职称",align:"center",prop:"title"}}),a("el-table-column",{attrs:{label:"审核状态",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[37===e.row.status?a("el-tag",{attrs:{type:"info"}},[t._v(t._s(e.row.inspectDescribe))]):137===e.row.status?a("el-tag",{attrs:{type:"danger"}},[t._v(t._s(e.row.inspectDescribe))]):t._e()]}}])}),a("el-table-column",{attrs:{label:"详情",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-button",{attrs:{type:"text",size:"small"},on:{click:function(a){return t.toDetails(e.row.applyId,e.row.applyTypeId,e.row.tutorId)}}},[t._v("查 看 ")])]}}])}),a("el-table-column",{attrs:{label:"备注",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-button",{attrs:{type:"text",size:"small"},on:{click:function(a){return t.commitFun(e.row)}}},[t._v("添加备注")]),a("el-button",{attrs:{type:"text",size:"small"},on:{click:function(a){return t.commitFind(e.row)}}},[t._v("查看备注")])]}}])})],1),a("el-row",{attrs:{type:"flex",justify:"center"}},[a("el-pagination",{staticStyle:{margin:"10px 0"},attrs:{"current-page":t.queryParams.pageNum,"page-size":t.queryParams.pageSize,layout:"total, prev, pager, next",total:t.totalData},on:{"size-change":t.handleSizeChange,"current-change":t.handleCurrentChange}})],1),a("el-row",[a("el-col",{attrs:{span:2,offset:22}},[a("el-button",{attrs:{type:"success",plain:"",size:"small",icon:"el-icon-success",loading:t.exportLoading},on:{click:function(e){return t.submitFun()}}},[t._v("提交 ")])],1)],1),a("el-dialog",{attrs:{title:"驳回备注(可以为空)",visible:t.dialogVisible,width:"20%"},on:{"update:visible":function(e){t.dialogVisible=e}}},[a("el-input",{attrs:{type:"textarea",autocomplete:"off"},model:{value:t.returnCommit,callback:function(e){t.returnCommit=e},expression:"returnCommit"}}),a("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(e){return t.cancel()}}},[t._v("取 消")]),a("el-button",{attrs:{type:"primary"},on:{click:function(e){return t.returnFun()}}},[t._v("确 定")])],1)],1),a("el-dialog",{attrs:{title:"驳回意见",visible:t.dialogVisibleReturn,width:"25%","show-close":!1,center:""},on:{"update:visible":function(e){t.dialogVisibleReturn=e}}},[a("el-descriptions",{staticClass:"margin-top",attrs:{column:1,border:"","label-class-name":"commit"}},[a("el-descriptions-item",{attrs:{label:"研究生院意见"}},[a("span",{staticStyle:{"white-space":"pre-wrap"}},[t._v(" "+t._s(t.commitYjsyCs)+" ")])]),a("el-descriptions-item",{attrs:{label:"社科处/科研处意见"}},[a("span",{staticStyle:{"white-space":"pre-wrap"}},[t._v(" "+t._s(t.commitSocial)+" ")])])],1),a("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{type:"primary"},on:{click:function(e){t.dialogVisibleReturn=!1}}},[t._v("确 定")])],1)],1)],1)},r=[],i=a("1da1"),s=(a("96cf"),a("2189")),o=a("6fa2"),l=a("a78e"),u=a.n(l),c={data:function(){return{commitSocial:"",commitYjsyCs:"",dialogVisibleReturn:!1,dialogVisible:!1,returnCommit:"",loading:!0,exportLoading:!1,single:!0,multiple:!0,showSearch:!0,totalData:0,secretaryInitList:[],applyTypeList:[],multipleSelection:[],commitArrays:[],pageNumber:1,queryParams:{userId:"",userName:"",organization:"",organizationName:"",applyType:"",subjectName:"",applyStatus:"",applyStatuss:[],subjectType:""},queryParamCopy:{},updateList:[],tutorList:[]}},created:function(){this.getSecretaryInit(),this.getApplyTypeList()},methods:{getApplyTypeList:function(){var t=this;return Object(i["a"])(regeneratorRuntime.mark((function e(){return regeneratorRuntime.wrap((function(e){while(1)switch(e.prev=e.next){case 0:Object(s["a"])().then((function(e){t.applyTypeList=e.data}));case 1:case"end":return e.stop()}}),e)})))()},getOrganizationId:function(){if(null!==u.a.get("organizationId"))return u.a.get("organizationId");console.log("error-organizationId is null")},getSecretaryInit:function(){var t=this;this.loading=!0;var e=this.getOrganizationId(),a=["37","137"];Object(s["b"])(e,a,this.pageNumber).then((function(e){t.tutorList=e.data.data,t.totalData=e.data.total,t.loading=!1})),this.resetQuery()},toDetails:function(t,e,a){Object(o["d"])(this,t,e,a)},search:function(){var t=this;this.pageNumber=1,""===this.queryParams.userId&&""===this.queryParams.applyStatus&&""===this.queryParams.userName&&""===this.queryParams.organization&&""===this.queryParams.applyType&&""===this.queryParams.subjectName&&""===this.queryParams.subjectType?this.getSecretaryInit():(""===this.queryParams.applyStatus&&(this.queryParams.applyStatuss=["37","137"]),this.queryParams.organization=this.getOrganizationId(),Object(s["c"])(this.queryParams,this.pageNumber).then((function(e){t.tutorList=e.data.data,t.totalData=e.data.total,t.loading=!1})).catch((function(t){throw t})))},changeApplyStatus:function(){this.queryParams.applyStatuss=[]},resetQuery:function(){this.queryParams.userId="",this.queryParams.userName="",this.queryParams.applyType="",this.queryParams.applyStatus="",this.queryParams.applyStatuss=[]},unPassFun:function(){this.check(137)},commitFun:function(t){this.commitArrays.length=0,this.dialogVisible=!0,this.returnCommit=t.commit;var e={id_1:0,status_1:0,commit_1:""};e.id_1=t.applyId,e.status_1=t.status,e.commit_1=t.commit,this.commitArrays.push(e)},returnFun:function(){this.commitArrays[0].commit_1=this.returnCommit,this.updateStatusFun(this.commitArrays,!1),this.dialogVisible=!1},commitFind:function(t){this.dialogVisibleReturn=!0,this.commitSocial=t.commitSocial,this.commitYjsyCs=t.commitYjsyCs},updateTutorListDataCommit:function(t){for(var e=0;e<this.tutorList.length;e++)if(t===this.tutorList[e].applyId){this.tutorList[e].commit=this.returnCommit;break}for(var a=0;a<this.updateList.length;a++)if(t===this.updateList[a].id_1){this.updateList[a].commit_1=this.returnCommit;break}},cancel:function(){this.dialogVisible=!1,this.returnCommit=null},check:function(t){if("submit"===t)for(var e=0;e<this.updateList.length;e++)this.updateList[e].status_1=this.updateList[e].status_1-123;else for(var a=0;a<this.updateList.length;a++)this.updateList[a].status_1=t;this.updateStatusFun(this.updateList,!0)},updateStatusFun:function(t,e){var a=this;Object(s["e"])(t).then((function(t){2e4===t.code&&a.$message.success("操作成功!"),e?a.getSecretaryInit():a.updateTutorListDataCommit(a.commitArrays[0].id_1)}))},submitFun:function(){var t=this;this.$confirm("确认提交吗？").then((function(e){for(var a=!0,n=0;n<t.updateList.length;n++)37===t.updateList[n].status_1&&(a=!1);0===t.updateList.length?t.$message.warning("未选择数据，请先进行选择！"):a?(t.check("submit"),t.getSecretaryInit()):t.$message.warning("有待审核的数据，请先进行审核！"),t.dialogVisiblePass=!1})).catch((function(){console.log("cancel")}))},handleSelectionChange:function(t){t.length>0?(this.single=!1,this.multiple=!1):(this.single=!0,this.multiple=!0),this.multipleSelection=t,this.updateObject(this.multipleSelection)},updateObject:function(t){this.updateList=[];for(var e=0;e<t.length;e++){var a={id_1:0,status_1:0,commit_1:""};a.id_1=t[e].applyId,a.status_1=t[e].status,a.commit_1=t[e].commit,this.updateList.push(a)}},handleSizeChange:function(t){},handleCurrentChange:function(t){this.pageNumber=t,this.getSecretaryInit()}}},p=c,h=(a("42b8"),a("2877")),d=Object(h["a"])(p,n,r,!1,null,null,null);e["default"]=d.exports},9861:function(t,e,a){"use strict";a("e260");var n=a("23e7"),r=a("d066"),i=a("0d3b"),s=a("6eeb"),o=a("e2cc"),l=a("d44e"),u=a("9ed3"),c=a("69f3"),p=a("19aa"),h=a("5135"),d=a("0366"),m=a("f5df"),f=a("825a"),y=a("861d"),g=a("7c73"),b=a("5c6c"),v=a("9a1f"),w=a("35a1"),S=a("b622"),k=r("fetch"),L=r("Headers"),_=S("iterator"),P="URLSearchParams",I=P+"Iterator",q=c.set,x=c.getterFor(P),z=c.getterFor(I),C=/\+/g,j=Array(4),R=function(t){return j[t-1]||(j[t-1]=RegExp("((?:%[\\da-f]{2}){"+t+"})","gi"))},T=function(t){try{return decodeURIComponent(t)}catch(e){return t}},N=function(t){var e=t.replace(C," "),a=4;try{return decodeURIComponent(e)}catch(n){while(a)e=e.replace(R(a--),T);return e}},O=/[!'()~]|%20/g,U={"!":"%21","'":"%27","(":"%28",")":"%29","~":"%7E","%20":"+"},F=function(t){return U[t]},D=function(t){return encodeURIComponent(t).replace(O,F)},A=function(t,e){if(e){var a,n,r=e.split("&"),i=0;while(i<r.length)a=r[i++],a.length&&(n=a.split("="),t.push({key:N(n.shift()),value:N(n.join("="))}))}},V=function(t){this.entries.length=0,A(this.entries,t)},E=function(t,e){if(t<e)throw TypeError("Not enough arguments")},$=u((function(t,e){q(this,{type:I,iterator:v(x(t).entries),kind:e})}),"Iterator",(function(){var t=z(this),e=t.kind,a=t.iterator.next(),n=a.value;return a.done||(a.value="keys"===e?n.key:"values"===e?n.value:[n.key,n.value]),a})),Y=function(){p(this,Y,P);var t,e,a,n,r,i,s,o,l,u=arguments.length>0?arguments[0]:void 0,c=this,d=[];if(q(c,{type:P,entries:d,updateURL:function(){},updateSearchParams:V}),void 0!==u)if(y(u))if(t=w(u),"function"===typeof t){e=t.call(u),a=e.next;while(!(n=a.call(e)).done){if(r=v(f(n.value)),i=r.next,(s=i.call(r)).done||(o=i.call(r)).done||!i.call(r).done)throw TypeError("Expected sequence with length 2");d.push({key:s.value+"",value:o.value+""})}}else for(l in u)h(u,l)&&d.push({key:l,value:u[l]+""});else A(d,"string"===typeof u?"?"===u.charAt(0)?u.slice(1):u:u+"")},J=Y.prototype;o(J,{append:function(t,e){E(arguments.length,2);var a=x(this);a.entries.push({key:t+"",value:e+""}),a.updateURL()},delete:function(t){E(arguments.length,1);var e=x(this),a=e.entries,n=t+"",r=0;while(r<a.length)a[r].key===n?a.splice(r,1):r++;e.updateURL()},get:function(t){E(arguments.length,1);for(var e=x(this).entries,a=t+"",n=0;n<e.length;n++)if(e[n].key===a)return e[n].value;return null},getAll:function(t){E(arguments.length,1);for(var e=x(this).entries,a=t+"",n=[],r=0;r<e.length;r++)e[r].key===a&&n.push(e[r].value);return n},has:function(t){E(arguments.length,1);var e=x(this).entries,a=t+"",n=0;while(n<e.length)if(e[n++].key===a)return!0;return!1},set:function(t,e){E(arguments.length,1);for(var a,n=x(this),r=n.entries,i=!1,s=t+"",o=e+"",l=0;l<r.length;l++)a=r[l],a.key===s&&(i?r.splice(l--,1):(i=!0,a.value=o));i||r.push({key:s,value:o}),n.updateURL()},sort:function(){var t,e,a,n=x(this),r=n.entries,i=r.slice();for(r.length=0,a=0;a<i.length;a++){for(t=i[a],e=0;e<a;e++)if(r[e].key>t.key){r.splice(e,0,t);break}e===a&&r.push(t)}n.updateURL()},forEach:function(t){var e,a=x(this).entries,n=d(t,arguments.length>1?arguments[1]:void 0,3),r=0;while(r<a.length)e=a[r++],n(e.value,e.key,this)},keys:function(){return new $(this,"keys")},values:function(){return new $(this,"values")},entries:function(){return new $(this,"entries")}},{enumerable:!0}),s(J,_,J.entries),s(J,"toString",(function(){var t,e=x(this).entries,a=[],n=0;while(n<e.length)t=e[n++],a.push(D(t.key)+"="+D(t.value));return a.join("&")}),{enumerable:!0}),l(Y,P),n({global:!0,forced:!i},{URLSearchParams:Y}),i||"function"!=typeof k||"function"!=typeof L||n({global:!0,enumerable:!0,forced:!0},{fetch:function(t){var e,a,n,r=[t];return arguments.length>1&&(e=arguments[1],y(e)&&(a=e.body,m(a)===P&&(n=e.headers?new L(e.headers):new L,n.has("content-type")||n.set("content-type","application/x-www-form-urlencoded;charset=UTF-8"),e=g(e,{body:b(0,String(a)),headers:b(0,n)}))),r.push(e)),k.apply(this,r)}}),t.exports={URLSearchParams:Y,getState:x}},"9a1f":function(t,e,a){var n=a("825a"),r=a("35a1");t.exports=function(t){var e=r(t);if("function"!=typeof e)throw TypeError(String(t)+" is not iterable");return n(e.call(t))}},a2a4:function(t,e,a){}}]);