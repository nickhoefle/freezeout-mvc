const addSectionButton = document.getElementById('addSectionButton');
    const sections = document.getElementById('sections');
    let sectionCount = 1;

    addSectionButton.addEventListener('click', () => {
        sectionCount++;

        const section = document.createElement('div');
        section.className = 'section';

        const label = document.createElement('label');
        label.textContent = `Song #${sectionCount}: `;

        const select = document.createElement('select');
        select.name = 'pdfFiles[]';
        select.required = true;

        const defaultOption = document.createElement('option');
        defaultOption.value = '';
        defaultOption.textContent = 'Select PDF File';

        // Fetch the PDF files from the server and populate the options dynamically
        fetch('/admin/setlists/get-files')
            .then(response => response.json())
            .then(data => {
                select.appendChild(defaultOption); // Add the default option as the first option

                data.forEach(file => {
                    const option = document.createElement('option');
                    option.value = file;
                    option.textContent = file;
                    select.appendChild(option);
                });

                section.appendChild(label);
                section.appendChild(document.createTextNode(' ')); // Add a space between the label and the dropdown
                section.appendChild(select);
                sections.appendChild(section);
            })
            .catch(error => {
                console.error('Error fetching PDF files:', error);
            });
    });