import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class LeafletFacade {
  public L: any = null;
  async initMap(containerId: string, lat: number, lng: number, zoom: number): Promise<void> {
    this.L = await import('leaflet');
    const map = this.L.map(containerId).setView([lat, lng], zoom);
    this.L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attribution: '&copy; OpenStreetMap contributors',
    }).addTo(map);
    this.L.marker([lat, lng]).addTo(map)
      .bindPopup('We are here!')
      .openPopup();
  }
}   
