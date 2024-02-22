import { Component } from '@angular/core';
import { Like } from 'src/app/models/Like';
import { LikeDTO } from 'src/app/models/LikeDTO';
import { User } from 'src/app/models/User';
import { LikeService } from 'src/app/services/like.service';
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
    userOne: {
      id: 0,
      username: '',
      password: '',
    },
    userTwoId: 0
  }
  likeDTO: LikeDTO = {
    userOneId: 0,
    userTwoId: 0
  }
  myLikes: number[] = []
  othersLikesMe: number[] = []

  constructor(private service: UserService, private likeService: LikeService) {}

  ngOnInit() {
    this.username = sessionStorage.getItem("myUsername")!
    this.service.getUsers().subscribe({
      next: res => {
        console.log(res)
        this.users = res
        console.log(this.users)
      }
    })
    this.getMyLikes()
    this.getOtherLikesMe()
  }

  giveLike(id: number) {
    let myId = parseInt(sessionStorage.getItem('myId')!)
    this.likeDTO.userOneId = myId
    this.likeDTO.userTwoId = id
    console.log(this.likeDTO)
    this.likeService.saveLikeDTO(this.likeDTO).subscribe({
      next: res => {
        if (res.match) {
         alert(`Has hecho match con el usuario ${id}`)
        }
        if (res.warning) {
          alert("Ya había un like dado a ese user")
        }
        window.location.reload()
      },
      error: err => console.log(err)
    })
  }

  getMyLikes() {
    let myId = parseInt(sessionStorage.getItem('myId')!)
    this.service.getMyLikes(myId).subscribe({
      next: res => {
        this.myLikes = res
        console.log(this.myLikes)
      },
      error: err => console.log(err)
    })
  }

  getOtherLikesMe() {
    let myId = parseInt(sessionStorage.getItem('myId')!)
    this.service.getOthersLikesMe(myId).subscribe({
      next: res => {
        this.othersLikesMe = res
        console.log(this.othersLikesMe)
      },
      error: err => console.log(err)
    })
  }

}
