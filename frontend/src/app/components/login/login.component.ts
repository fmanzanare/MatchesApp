import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/User';
import { UserService } from 'src/app/services/user.service';
import { HomeComponent } from '../home/home.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  username: string = ''
  password: string = ''
  user: User = {
    id: 0,
    username: '',
    password: ''
  }

  constructor(private router: Router, private service: UserService) {}

  login() {
    if (this.username == '' || this.password == '') {
      alert("Debes introducir un usuario y una contraseña para poder identificarte")
      return
    }

    this.user.username = this.username
    this.user.password = this.password

    this.service.login(this.user).subscribe({
      next: res => {
        console.log(res)
        sessionStorage.setItem('myId', res.user_id)
        sessionStorage.setItem('myUsername', this.username)
        this.router.navigate(['home'])
      },
      error: err => console.log(err)
    })

  }



}
