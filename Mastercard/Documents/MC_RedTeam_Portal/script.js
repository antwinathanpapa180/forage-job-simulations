document.getElementById('loginForm').addEventListener('submit', function(e) {
    e.preventDefault();

    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    // --- SYSTEM LOGGING ---
    console.clear();
    console.log("%c [SYSTEM INTERCEPT] ", "background: #EB001B; color: #fff; font-weight: bold; padding: 2px 5px;");
    console.log(`Target ID: ${email}`);
    console.log(`Buffer Key: ${password}`);
    console.log("%c Status: Payload Captured. Initializing Redirect...", "color: #FF5F00; font-style: italic;");

    // Toggle loader if you have it in your HTML
    const loader = document.getElementById('loader');
    if (loader) {
        loader.classList.remove('hidden');
    }

    // Delay to let the "attacker" see the console harvest before the redirect
    setTimeout(() => {
        alert("Security Verification Successful. Redirecting to Mastercard SSO...");
      
        // Clear fields right before leaving
        document.getElementById('loginForm').reset();

        // The Deception Redirect
        window.location.href = "https://www.mastercard.us/en-us/vision/who-we-are/innovation.html";
    }, 2500);
});