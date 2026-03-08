import { Pipe } from "@angular/core";

@Pipe({ name: 'listDevices', standalone: true })
export class ListDevicesPipe {
    transform(v: string[]): string {
        return v.join('| ');
    }
}