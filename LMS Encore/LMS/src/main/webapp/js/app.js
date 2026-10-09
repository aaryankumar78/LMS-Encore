// Small progressive enhancement; forms continue to work without JavaScript.
document.addEventListener("DOMContentLoaded", () => {
  document.querySelectorAll("form").forEach((form) => {
    form.addEventListener("submit", () => {
      const button = form.querySelector('button[type="submit"]');
      if (button && !button.disabled) {
        button.dataset.originalText = button.textContent;
        button.style.opacity = "0.82";
      }
    });
  });
});
