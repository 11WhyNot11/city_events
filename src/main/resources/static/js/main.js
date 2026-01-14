(() => {
    const path = window.location.pathname;
    document.querySelectorAll(".nav-link").forEach(a => {
        const href = a.getAttribute("href");
        if (href && href !== "/" && path.startsWith(href)) a.classList.add("active");
        if (href === "/" && path === "/") a.classList.add("active");
    });
})();
