function toggleDarkMode(clickedButton) {
    let chordPage = clickedButton.nextElementSibling;

    let computedColor = window.getComputedStyle(chordPage).color;

    if (computedColor === 'rgb(0, 0, 0)' || computedColor === 'black') {
        chordPage.style.color = 'white';
        chordPage.style.backgroundColor = 'black';
    } else {
        chordPage.style.color = 'black';
        chordPage.style.backgroundColor = 'white';
    }
}
