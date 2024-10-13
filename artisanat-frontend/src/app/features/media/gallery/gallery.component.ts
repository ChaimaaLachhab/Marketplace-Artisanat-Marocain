import { Component } from '@angular/core';
import { DecimalPipe, NgForOf, NgIf } from "@angular/common";
import { HttpClient, HttpEventType, HttpHeaders } from "@angular/common/http";

@Component({
  selector: 'app-gallery',
  standalone: true,
  imports: [
    NgIf,
    NgForOf,
    DecimalPipe
  ],
  templateUrl: './gallery.component.html',
  styleUrls: ['./gallery.component.css']
})
export class GalleryComponent {
  files: any[] = [];
  isVideoSelected = false;

  onFileSelect(event: any) {
    const selectedFiles = Array.from(event.target.files) as File[];
    this.processFiles(selectedFiles);
  }

  processFiles(files: File[]) {
    for (const file of files) {
      if (file.type.startsWith('video')) {
        if (!this.isVideoSelected && this.files.length < 10) {
          this.addFile(file, true);
          break;
        } else {
          alert('Only one video is allowed.');
        }
      } else if (file.type.startsWith('image')) {
        const maxImages = this.isVideoSelected ? 9 : 10;
        if (this.files.filter(f => !f.isVideo).length < maxImages) {
          this.addFile(file);
        } else {
          alert(`You can upload a maximum of ${maxImages} images.`);
          break;
        }
      } else {
        alert('Invalid file type.');
      }
    }
  }

  addFile(file: File, isVideo = false) {
    const reader = new FileReader();
    reader.onload = (e) => {
      this.files.push({ file, preview: e.target?.result, isVideo });
      if (isVideo) {
        this.isVideoSelected = true;
      }
    };
    reader.readAsDataURL(file);
  }

  removeFile(index: number, event: Event) {
    event.stopPropagation();
    const removedFile = this.files.splice(index, 1)[0];
    if (removedFile.isVideo) {
      this.isVideoSelected = false;
    }
  }

  onClear() {
    this.files = [];
    this.isVideoSelected = false;
  }
}
