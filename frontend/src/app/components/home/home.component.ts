import { Component } from '@angular/core';
import { Like } from 'src/app/models/Like';
import { User } from 'src/app/models/User';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  username: string = ''
  users: User[] = []
  like: Like = {
    userOneId: 0,
    userTwoId: 0
  }

  constructor(private service: UserService) {}

  ngOnInit() {
    this.username = sessionStorage.getItem("myUsername")!
    this.service.getUsers().subscribe({
      next: res => {
        console.log(res)
        this.users = res
        console.log(this.users)
      }
    })
  }

  likesMe(id: number) {
    this.like.userOneId = parseInt(sessionStorage.getItem('myId')!)
    this.like.userTwoId = id

    console.log(this.like)

    this.service.like(this.like).subscribe({
      next: res => console.log(res),
      error: err => console.log(err)
    })
  }

}
