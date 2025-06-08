import { Component, OnInit } from '@angular/core';
import { Image } from '../../interfaces/image';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  images: Image[] = [];
  private socket!: WebSocket;

  constructor() {}

  ngOnInit() {
    this.socket = new WebSocket('ws://localhost:8080/ws/images');

    this.socket.addEventListener('open', () => {
      console.log('WebSocket connection opened');
      this.socket.send('Client connected');
    });

    this.socket.addEventListener('message', (event) => {
      const message = JSON.parse(event.data) as Image;
      this.images.push(message);
      console.log('Message received:', message);
    });

    this.socket.addEventListener('error', (event) => {
      console.error('WebSocket error:', event);
    });

    this.socket.addEventListener('close', () => {
      console.log('WebSocket connection closed');
    });
  }
}
