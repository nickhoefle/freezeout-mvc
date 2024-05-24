document.addEventListener('DOMContentLoaded', function() {
    // Function to create a snowflake element
    function createSnowflake() {
        const snowflake = document.createElement("div");
        snowflake.className = "snowflake";

        const size = Math.random() * 3 + 1; // Random size
        snowflake.style.width = `${size}px`;
        snowflake.style.height = `${size}px`;

        const startX = Math.random() * window.innerWidth;
        snowflake.style.left = `${startX}px`;
        snowflake.style.top = "-10px"; // Start above the viewport

        const animationDuration = Math.random() * 5 + 2; // Random duration between 2 and 7 seconds
        snowflake.style.animation = `fall ${animationDuration}s linear infinite`;

        return snowflake;
    }

    // Function to add snowflakes to the container
    function addSnowflakes() {
        const container = document.querySelector(".snowfall-container");
        const numSnowflakes = 30; // Adjust the number of snowflakes

        for (let i = 0; i < numSnowflakes; i++) {
            const snowflake = createSnowflake();
            container.appendChild(snowflake);
        }
    }

    addSnowflakes();
});