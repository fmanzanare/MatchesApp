import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';

const routes: Routes = [
  { path:'', component: LoginComponent, title:'MatchesApp - Inicia sesión y conoce a tu alma gemela' },
  { path:'home', component: HomeComponent, title:'MatchesApp - Empieza a dar likes como loco!!!' }
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
