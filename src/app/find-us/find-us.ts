import { Component, Inject, PLATFORM_ID } from '@angular/core';
import { AfterViewInit } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { LeafletFacade } from '../services/leaflet-facade';

@Component({
  selector: 'app-find-us',
  imports: [],
  templateUrl: './find-us.html',
  styleUrl: './find-us.css',
})
export class FindUs implements AfterViewInit {
  public L = null;
  constructor(@Inject(PLATFORM_ID) private platformId: any, private leafletFacade: LeafletFacade) {}
  ngAfterViewInit() {
      if (isPlatformBrowser(this.platformId)) {
        console.log('Initializing Leaflet map');
        this.leafletFacade.initMap('map', 51.505, -0.09, 13);
      }
    }
}
