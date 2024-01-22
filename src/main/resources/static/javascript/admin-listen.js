document.addEventListener("DOMContentLoaded", function() {
    const showBtn = document.getElementById("showUploadedSongsButton");
    const songsDiv = document.getElementById("uploadedSongsDiv");

    showBtn.addEventListener("click", function() {

        if (songsDiv.style.display === "none") {
            songsDiv.style.display = "block";
            showBtn.textContent = "Hide Uploaded Files";
            showBtn.classList.add("btn-danger"); // add the class
        } else {
            songsDiv.style.display = "none";
            showBtn.textContent = "Show Uploaded Files";
            showBtn.classList.remove("btn-danger"); // remove the class
        }
    });
});