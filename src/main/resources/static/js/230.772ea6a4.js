"use strict";(self["webpackChunkourmemory"]=self["webpackChunkourmemory"]||[]).push([[230],{2230:function(t,e,a){a.r(e),a.d(e,{default:function(){return y}});a(7658);var o=a(3396),l=a(2268),s=a(4870),n=a(9242),i=a(2583),u=a(7627),r=a(3824);const c={class:"post-form-wrap"},d={class:"post-form"},m={class:"post-form-header"},v={class:"mb-3 mt-3"},p={class:"post-form-body"},f={class:"mb-3"};var _=(0,o.aZ)({name:"EditPostView",setup(t){const e=(0,s.iH)(u.Z.state.userId),a=(0,s.iH)(u.Z.state.groupData.id),_=(0,s.iH)(u.Z.state.postData.id),b=(0,s.iH)(""),w=(0,s.iH)(""),y=(0,s.iH)({postId:0,user:{id:0,name:"",nickName:"",email:"",role:""},title:"",content:"",createdDate:"",createdDateSimple:"",modifiedDate:"",thumbnailPath:"",uploads:[{id:0,fileName:"",filePath:""}]}),h=async function(){const t=await(0,i.d5)(_.value);y.value=t.data,b.value=y.value.title,w.value=y.value.content},Z=function(){u.Z.commit("clearPost"),r.Z.push(`/${e.value}/${a.value}`)},k=async function(){try{const t={postId:y.value.postId,title:b.value,content:w.value};await(0,i.Kj)(t),await r.Z.replace(`/${e.value}/${a.value}/${_.value}`)}catch(t){console.log(t)}};return(0,o.bv)(h),(t,e)=>((0,o.wg)(),(0,o.iD)("div",c,[(0,o._)("div",null,[(0,o._)("h4",{onClick:Z},(0,l.zw)((0,s.SU)(u.Z).state.groupData.name),1)]),(0,o._)("div",d,[(0,o._)("div",m,[(0,o._)("div",v,[(0,o.wy)((0,o._)("input",{type:"text",class:"form-control",id:"exampleFormControlInput1","onUpdate:modelValue":e[0]||(e[0]=t=>b.value=t)},null,512),[[n.nr,b.value]])])]),(0,o._)("div",p,[(0,o._)("div",f,[(0,o.wy)((0,o._)("textarea",{class:"form-control",id:"exampleFormControlTextarea1",rows:"20",style:{resize:"none"},"onUpdate:modelValue":e[1]||(e[1]=t=>w.value=t)},null,512),[[n.nr,w.value]])])]),(0,o._)("div",{class:"post-form-footer"},[(0,o._)("div",{class:"post-submit"},[(0,o._)("button",{type:"button",class:"btn btn-secondary",onClick:k},"수정")])])])]))}}),b=a(89);const w=(0,b.Z)(_,[["__scopeId","data-v-340edbf6"]]);var y=w}}]);
//# sourceMappingURL=230.772ea6a4.js.map