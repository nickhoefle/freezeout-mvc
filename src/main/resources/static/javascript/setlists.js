document.addEventListener("DOMContentLoaded", function () {

    const addAnotherFileButton = document.getElementById('addAnotherFileButton');
    const songFileDropdowns = document.getElementById('songFileDropdowns');
    let songFileCount = 1;

    addAnotherFileButton.addEventListener('click', () => {
        songFileCount++;

        const songFileDropdownDiv = document.createElement('div');
        songFileDropdownDiv.className = 'songFileDropdownDiv';

        const label = document.createElement('label');
        label.textContent = `Song #${songFileCount}:`;

        const songFilesDropdown = document.createElement('select');
        songFilesDropdown.name = 'pdfFiles[]';
        songFilesDropdown.required = true;

        const defaultOption = document.createElement('option');
        defaultOption.value = '';
        defaultOption.textContent = 'Select PDF File';

      // Fetch the PDF files from the server and populate the options dynamically
        fetch('/admin/setlists/get-files')
        .then(response => response.json())
        .then(data => {
            data.sort(); // Sort the PDF files alphabetically

            songFilesDropdown.appendChild(defaultOption); // Add the default option as the first option

            data.forEach(file => {
                const option = document.createElement('option');
                option.value = file;
                option.textContent = file;
                songFilesDropdown.appendChild(option);
            });

            songFileDropdownDiv.appendChild(label);
            songFileDropdownDiv.appendChild(document.createTextNode(' ')); // Add a space between the label and the dropdown
            songFileDropdownDiv.appendChild(songFilesDropdown);
            songFileDropdowns.appendChild(songFileDropdownDiv);
        })
        .catch(error => {
            console.error('Error fetching PDF files:', error);
        });
    });

});