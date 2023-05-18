import{_,o,c,a as s,F as g,f as M,n as H,t as n,d as w,g as f,e as p,h as V,i as A,r as h,b as x}from"./_plugin-vue_export-helper-1c9e2469.js";import{N as y,U as B}from"./NavBar-2d3ee91b.js";const b={__name:"DashBoard",data(){return{active:"materias",items:[{id:"materias",nombre:"Materias",icon:'<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"><path d="M4 8H20V5H4V8ZM14 19V10H10V19H14ZM16 19H20V10H16V19ZM8 19V10H4V19H8ZM3 3H21C21.5523 3 22 3.44772 22 4V20C22 20.5523 21.5523 21 21 21H3C2.44772 21 2 20.5523 2 20V4C2 3.44772 2.44772 3 3 3Z"></path></svg>'},{id:"mapa-curricular",nombre:"Mapa curricular",icon:'<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"><path d="M18 3C19.6569 3 21 4.34315 21 6C21 7.65685 19.6569 9 18 9H15C13.6941 9 12.5831 8.16562 12.171 7.0009L11 7C9.9 7 9 7.9 9 9L9.0009 9.17102C10.1656 9.58312 11 10.6941 11 12C11 13.3059 10.1656 14.4169 9.0009 14.829L9 15C9 16.1 9.9 17 11 17L12.1707 17.0001C12.5825 15.8349 13.6937 15 15 15H18C19.6569 15 21 16.3431 21 18C21 19.6569 19.6569 21 18 21H15C13.6941 21 12.5831 20.1656 12.171 19.0009L11 19C8.79 19 7 17.21 7 15H5C3.34315 15 2 13.6569 2 12C2 10.3431 3.34315 9 5 9H7C7 6.79086 8.79086 5 11 5L12.1707 5.00009C12.5825 3.83485 13.6937 3 15 3H18ZM18 17H15C14.4477 17 14 17.4477 14 18C14 18.5523 14.4477 19 15 19H18C18.5523 19 19 18.5523 19 18C19 17.4477 18.5523 17 18 17ZM8 11H5C4.44772 11 4 11.4477 4 12C4 12.5523 4.44772 13 5 13H8C8.55228 13 9 12.5523 9 12C9 11.4477 8.55228 11 8 11ZM18 5H15C14.4477 5 14 5.44772 14 6C14 6.55228 14.4477 7 15 7H18C18.5523 7 19 6.55228 19 6C19 5.44772 18.5523 5 18 5Z"></path></svg>'},{id:"elecciones",nombre:"Elecciones",icon:'<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"><path d="M5.45455 15L1 18.5V3C1 2.44772 1.44772 2 2 2H17C17.5523 2 18 2.44772 18 3V15H5.45455ZM4.76282 13H16V4H3V14.3851L4.76282 13ZM8 17H18.2372L20 18.3851V8H21C21.5523 8 22 8.44772 22 9V22.5L17.5455 19H9C8.44772 19 8 18.5523 8 18V17Z"></path></svg>'},{id:"ayuda",nombre:"Ayuda",icon:'<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"><path d="M12 22C6.47715 22 2 17.5228 2 12C2 6.47715 6.47715 2 12 2C17.5228 2 22 6.47715 22 12C22 17.5228 17.5228 22 12 22ZM12 20C16.4183 20 20 16.4183 20 12C20 7.58172 16.4183 4 12 4C7.58172 4 4 7.58172 4 12C4 16.4183 7.58172 20 12 20ZM11 15H13V17H11V15ZM13 13.3551V14H11V12.5C11 11.9477 11.4477 11.5 12 11.5C12.8284 11.5 13.5 10.8284 13.5 10C13.5 9.17157 12.8284 8.5 12 8.5C11.2723 8.5 10.6656 9.01823 10.5288 9.70577L8.56731 9.31346C8.88637 7.70919 10.302 6.5 12 6.5C13.933 6.5 15.5 8.067 15.5 10C15.5 11.5855 14.4457 12.9248 13 13.3551Z"></path></svg>'}]}},computed:{console:()=>console,window:()=>window,document:()=>document},methods:{changeActive(t){this.$emit("active-changed",t),this.active=t}}},Z={id:"dashboard"},E={id:"menu"},L=["id","onClick"],D=["innerHTML"],N={class:"dashboard-label"};function S(t,i,r,a,l,d){return o(),c("section",Z,[s("ul",E,[(o(!0),c(g,null,M(l.items,e=>(o(),c("li",{class:H(["item",{active:this.active==e.id}]),id:e.id,onClick:u=>d.changeActive(e.id)},[s("i",{class:"dashboard-icon",innerHTML:e.icon},null,8,D),s("a",N,n(e.nombre),1)],10,L))),256))])])}const j=_(b,[["render",S]]);const I={name:"Materias",props:{materias:Array}},P={class:"content"},k=w('<h2 class="HeaderMaterias">Materias Pendientes</h2><div class="container-wrapper"><div class="container1"><p>PG - Promedio General</p><p>ES - Estado</p><p>CR - Creditos</p><p>B - Bajas</p><p>A - Aprobación</p></div><div class="container2"><p>R - Requisitos</p><p>C - Cursados</p><p>SEM - Semestre</p><p>I - Intentos</p></div></div>',2),R={class:"materias-table"},G=s("colgroup",null,[s("col",{span:"1",class:"nombre-materia"}),s("col",{span:"1",class:"datos-materia"}),s("col",{span:"1",class:"datos-materia"}),s("col",{span:"1",class:"datos-materia"}),s("col",{span:"1",class:"datos-materia"}),s("col",{span:"1",class:"datos-materia"}),s("col",{span:"1",class:"datos-materia"}),s("col",{span:"1",class:"datos-materia"}),s("col",{span:"1",class:"datos-materia"}),s("col",{span:"1",class:"datos-materia"})],-1),T=s("thead",null,[s("tr",null,[s("th",{class:"nombre-materia"},"Materias Pendientes"),s("th",null,"PG"),s("th",null,"ES"),s("th",null,"CR"),s("th",null,"B"),s("th",null,"A"),s("th",null,"R"),s("th",null,"C"),s("th",null,"SEM"),s("th",null,"I")])],-1);function U(t,i,r,a,l,d){return o(),c("div",P,[k,s("table",R,[G,T,s("tbody",null,[(o(!0),c(g,null,M(r.materias,e=>(o(),c("tr",{key:e.id},[s("td",null,n(e.descripcion),1),s("td",null,n(e.promedioMateria),1),s("td",null,n(e.estado),1),s("td",null,n(e.creditos),1),s("td",null,n(e.alumnosBajas),1),s("td",null,n(e.porcentajeDeAprobacion),1),s("td",null,n(e.req),1),s("td",null,n(e.alumnosInscritos),1),s("td",null,n(e.semestre),1),s("td",null,n(e.intentos),1)]))),128))])])])}const m=_(I,[["render",U]]);const q={name:"MapaCurricular"},F={class:"content"},z=s("h2",{class:"titulo"},"Mapa Curricular",-1),O=s("h2",{class:"carrera"},[f("Ingeniería en "),s("span",{class:"blue"},"Sistemas de Información")],-1),Y=[z,O];function J(t,i,r,a,l,d){return o(),c("div",F,Y)}const C=_(q,[["render",J]]),K={name:"Elecciones"},Q={class:"content"},W=s("h1",null,"ELECCIONES",-1),X=s("p",null,"Esta es la página de elecciones",-1),s1=[W,X];function e1(t,i,r,a,l,d){return o(),c("div",Q,s1)}const v=_(K,[["render",e1]]),t1={name:"Ayuda"},a1={class:"content"},n1=s("h1",null,"AYUDAAAAAA",-1),o1=[n1];function c1(t,i,r,a,l,d){return o(),c("div",a1,o1)}const $=_(t1,[["render",c1]]);async function i1(t,i){const a=await(await fetch(`/api/alumno/datos-alumno/${t}`)).json();i(a),console.log(a)}async function r1(t,i){const a=await(await fetch(`/api/materia/materias-pendientes/${t}`)).json();i(a),console.log(a)}const l1={components:{NavBar:y,UserMenu:B,DashBoard:j,Materias:m,MapaCurricular:C,Elecciones:v,Ayuda:$},data(){return{expediente:221202715,user:{},materias:[],active:"materias",views:{materias:m,"mapa-curricular":C,elecciones:v,ayuda:$}}},methods:{changeView(t){this.active=t},loadDatosAlumno(t){this.user=t},loadMateriasAlumnos(t){this.materias=t}},mounted(){i1(this.expediente,this.loadDatosAlumno),r1(this.expediente,this.loadMateriasAlumnos)}},d1={id:"panel"};function _1(t,i,r,a,l,d){const e=h("NavBar"),u=h("DashBoard");return o(),c("main",d1,[p(e,{userData:this.user},null,8,["userData"]),p(u,{onActiveChanged:d.changeView},null,8,["onActiveChanged"]),(o(),V(A(l.views[l.active]),{materias:this.materias},null,8,["materias"]))])}const u1=_(l1,[["render",_1]]);x(u1).mount("#app");
