function toggleNotesChordPage() {
    notesDiv = document.getElementById("notesDiv").style.display;
    if (notesDiv === "" ) {
        notesDiv = "none";
    } else {
        notesDiv = "";
    }
}

function printText(button) {
    var chordsTextElement = button.closest('td').querySelector('.chordsText');

    if (chordsTextElement) {
        var chordsText = chordsTextElement.innerText || chordsTextElement.textContent;

        // Create a new window for printing
        var printWindow = window.open('', '_blank');
        printWindow.document.write('<html><head><title>Chords Text</title></head><body>');
        printWindow.document.write('<pre>' + escapeHTML(chordsText) + '</pre>');
        printWindow.document.write('</body></html>');
        printWindow.document.close();

        // Trigger the print dialog
        printWindow.print();
    } else {
        console.error('Error: Chords text element not found.');
    }
}

// Function to escape HTML characters
function escapeHTML(html) {
    var escapeElement = document.createElement('div');
    escapeElement.textContent = html;
    return escapeElement.innerHTML;
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