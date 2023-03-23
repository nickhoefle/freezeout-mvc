function toggleNotesChordPage() {
    notesDiv = document.getElementById("notesDiv").style.display;
    if (notesDiv === "" ) {
        notesDiv = "none";
    } else {
        notesDiv = "";
    }
}

function printText(input) {
    let i = Number(`${input.parentNode.parentNode.rowIndex}`);
    const chordsText = document.getElementsByClassName("chordsText");
    const w = window.open();
    w.document.write('<div style="white-space: pre-wrap">' + chordsText[i].innerHTML + '</div>');
    w.print();
    w.close();
}

function toggleNotesChordPage(clicked) {
    let notesDiv = document.getElementById("notesDiv");
    let notesButton = document.getElementById("notesButton");
    let chordsDiv = document.getElementById("chordsDiv");
    let chordsButton = document.getElementById("chordsButton");

    if (clicked === 'notes' && notesDiv.style.display === "") {
        chordsButton.style.border = "none";
        return;
    }

    if (clicked === 'notes' && notesDiv.style.display === "none") {
        notesDiv.style.display = "";
        notesButton.style.border = "5px solid black";
        chordsDiv.style.display = "none";
        chordsButton.style.border = "none";
        return;
    }

    if (clicked === 'chords' && chordsDiv.style.display === "") {
        chordsButton.style.border = "5px solid black";
        notesButton.style.border = "none";
        return;
    }

    if (clicked === 'chords' && chordsDiv.style.display === "none") {
        chordsDiv.style.display = "";
        chordsButton.style.border = "5px solid black";
        notesDiv.style.display = "none";
        notesButton.style.border = "none";
        return;
    }
}