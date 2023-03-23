const upcomingGigSlideIndex = 1;
showUpcomingGigDivs(upcomingGigSlideIndex);

function plusUpcomingGigDivs(n) {
    showUpcomingGigDivs(upcomingGigSlideIndex += n);
}

function showUpcomingGigDivs(n) {
    const i;
    const x = document.getElementsByClassName("upcomingGigSlides");
    if (n > x.length) {upcomingGigSlideIndex = 1}
    if (n < 1) {upcomingGigSlideIndex = x.length}
    for (i = 0; i < x.length; i++) {
        x[i].style.display = "none";
    }
    x[upcomingGigSlideIndex-1].style.display = "block";
}

const pastGigSlideIndex = 1;
showPastGigDivs(pastGigSlideIndex);

function plusPastGigDivs(n) {
    showPastGigDivs(pastGigSlideIndex += n);
}

function showPastGigDivs(n) {
    const i;
    const x = document.getElementsByClassName("pastGigSlides");
    if (n > x.length) {pastGigSlideIndex = 1}
    if (n < 1) {pastGigSlideIndex = x.length}
    for (i = 0; i < x.length; i++) {
        x[i].style.display = "none";
    }
    x[pastGigSlideIndex-1].style.display = "block";
}