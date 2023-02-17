let currentSlide = 1;
setInterval(showSlides, 5000);

setTimeout(function() {
    let slideIndex = 0;
    showSlides();
}, 1);

function showSlides() {
  let slides = document.getElementsByClassName("photoGallerySlide");
  let previousSlideIndex = currentSlide - 1;
  if (previousSlideIndex < 0) {
    previousSlideIndex = slides.length - 1;
  }
  slides[previousSlideIndex].classList.remove("active");
  currentSlide++;
  if (currentSlide > slides.length) {
    currentSlide = 1;
  }
  let currentSlideIndex = currentSlide - 1;
  slides[currentSlideIndex].classList.add("active");
}

