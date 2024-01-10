function zoomInChordPage(clickedButton) {
    let chordPage = clickedButton.nextElementSibling.nextElementSibling;
    let chordPageFontSize = parseInt(chordPage.style.fontSize);
    let chordPageNewFontSize = chordPageFontSize + 1;
    chordPage.style.fontSize = chordPageNewFontSize + "px";
}

function zoomOutChordPage(clickedButton) {
    let chordPage = clickedButton.nextElementSibling;
    let chordPageFontSize = parseInt(chordPage.style.fontSize);
    let chordPageNewFontSize = chordPageFontSize - 1;
    chordPage.style.fontSize = chordPageNewFontSize + "px";
}