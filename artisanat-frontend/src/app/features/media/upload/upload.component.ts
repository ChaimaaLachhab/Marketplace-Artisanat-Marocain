import { Component } from '@angular/core';
import { MessageService, PrimeNGConfig} from 'primeng/api';
import {FileRemoveEvent, FileSelectEvent, FileUploadModule} from 'primeng/fileupload';
import { ButtonModule } from 'primeng/button';
import { CommonModule } from '@angular/common';
import { BadgeModule } from 'primeng/badge';
import { HttpClientModule } from '@angular/common/http';
import { ProgressBarModule } from 'primeng/progressbar';
import { ToastModule } from 'primeng/toast';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrl: './upload.component.css',
  standalone: true,
  imports: [FileUploadModule, ButtonModule, BadgeModule, ProgressBarModule, ToastModule, HttpClientModule, CommonModule],
  providers: [MessageService]
})
export class UploadComponent {
  files: File[] = [];

  totalSize: number = 0;

  totalSizePercent: number = 0;

  constructor(private config: PrimeNGConfig, private messageService: MessageService) {}

  choose(event: Event, callback: () => void): void {
    callback();
  }

  onRemoveTemplatingFile(event: Event, file: File, removeFileCallback: (event: FileRemoveEvent) => void, index: number): void {
    removeFileCallback({ originalEvent: event, file: file });
    this.totalSize -= file.size;
    this.totalSizePercent = this.totalSize / 10000;
  }

  onClearTemplatingUpload(clear: () => void): void {
    clear();
    this.totalSize = 0;
    this.totalSizePercent = 0;
  }

  onTemplatedUpload(): void {
    this.messageService.add({ severity: 'info', summary: 'Success', detail: 'File Uploaded', life: 3000 });
  }

  onSelectedFiles(event: FileSelectEvent): void {
    this.files = [...(this.files || []), ...event.files];
    this.updateTotalSize();
  }

  updateTotalSize(): void {
    this.totalSize = this.files.reduce((sum, file) => sum + file.size, 0);
    this.totalSizePercent = this.totalSize / 10000;
  }

  uploadEvent(callback: () => void): void {
    callback();
  }

  formatSize(bytes: number): string {
    const k = 1024;
    const dm = 3;
    const sizes = this.config.translation?.fileSizeTypes || ['Bytes', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'];
    if (bytes === 0) {
      return `0 ${sizes[0]}`;
    }

    const i = Math.floor(Math.log(bytes) / Math.log(k));
    const formattedSize = parseFloat((bytes / Math.pow(k, i)).toFixed(dm));

    return `${formattedSize} ${sizes[i]}`;
  }
}
