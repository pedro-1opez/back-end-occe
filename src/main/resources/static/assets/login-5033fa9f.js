import{_ as r,o as i,c as l,a as n,d as _,e as t,F as f,r as a,b as v}from"./_plugin-vue_export-helper-1c9e2469.js";import{N as b}from"./NavBar-2d3ee91b.js";const M={methods:{onMouseDown(){this.$refs.btn.classList.add("btn--active")},onMouseUp(){this.$refs.btn.classList.remove("btn--active")},onMouseClick(){location.href="https://htmlcolorcodes.com/es/"}}},L={id:"fomLogin"},C=n("h1",null,"ACCESO A LA PLATAFORMA",-1),g=_('<div class="data-entry"><div class="labels"><label for="email">Correo Electrónico Institucional:</label><label for="password">Contraseña:</label></div><div class="inputs"><input type="text" id="email" name="email" required><input type="password" id="password" name="password" required></div></div>',1);function w(c,o,d,u,p,e){return i(),l("div",L,[n("section",null,[C,n("form",null,[g,n("button",{class:"btn",onMousedown:o[0]||(o[0]=(...s)=>e.onMouseDown&&e.onMouseDown(...s)),onMouseup:o[1]||(o[1]=(...s)=>e.onMouseUp&&e.onMouseUp(...s)),onClick:o[2]||(o[2]=(...s)=>e.onMouseClick&&e.onMouseClick(...s))},"ENTRAR",32)])])])}const A=r(M,[["render",w]]),N={components:{NavBar:b,FormLogin:A}};function h(c,o,d,u,p,e){const s=a("NavBar"),m=a("FormLogin");return i(),l(f,null,[t(s,{userData:this.user},null,8,["userData"]),t(m)],64)}const k=r(N,[["render",h]]);v(k).mount("#app");