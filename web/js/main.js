/**
 * Main JavaScript for Laptop Store
 */

// Add to Cart functionality with animation
$(document).ready(function () {
    // Add to cart click handler with improved animation
    $(".addToCart").click(function (e) {
        e.preventDefault(); // Prevent default action

        // Add visual feedback for button click
        const $button = $(this);
        $button.addClass('clicked');
        setTimeout(() => {
            $button.removeClass('clicked');
        }, 300);

        var pid = $(this).data("pid");

        // Show toast notification
        showToast("Adding product to cart...");

        $.ajax({
            url: "CartURL",
            type: "GET",
            data: {
                service: "add2cart",
                pid: pid
            },
            success: function (response) {
                // Show success toast
                showToast("Product added to cart successfully!", "success");

                // Add product fly animation
                animateAddToCart($button);
            },
            error: function (xhr, status, error) {
                // Show error toast
                showToast("Error adding product to cart: " + error, "error");
            }
        });
    });

    // Initialize tooltips
    if (typeof bootstrap !== 'undefined' && bootstrap.Tooltip) {
        const tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
        tooltipTriggerList.map(function (tooltipTriggerEl) {
            return new bootstrap.Tooltip(tooltipTriggerEl);
        });
    }

    // Add hover effects for product rows
    $('.table tbody tr').hover(
        function () {
            $(this).addClass('table-hover-highlight');
        },
        function () {
            $(this).removeClass('table-hover-highlight');
        }
    );
});

// Toast notification function
function showToast(message, type = "info") {
    // Check if toast container exists, if not create it
    if ($('#toastContainer').length === 0) {
        $('body').append('<div id="toastContainer" class="position-fixed bottom-0 end-0 p-3" style="z-index: 11"></div>');
    }

    // Set toast color based on type
    let bgColor = "bg-primary";
    let icon = '<i class="fas fa-info-circle me-2"></i>';

    if (type === "success") {
        bgColor = "bg-success";
        icon = '<i class="fas fa-check-circle me-2"></i>';
    } else if (type === "error") {
        bgColor = "bg-danger";
        icon = '<i class="fas fa-exclamation-circle me-2"></i>';
    }

    // Create toast HTML
    const toastId = "toast-" + Date.now();
    const toastHtml = `
        <div id="${toastId}" class="toast align-items-center text-white ${bgColor} border-0" role="alert" aria-live="assertive" aria-atomic="true">
            <div class="d-flex">
                <div class="toast-body">
                    ${icon} ${message}
                </div>
                <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
            </div>
        </div>
    `;

    // Add toast to container
    $('#toastContainer').append(toastHtml);

    // Initialize and show toast
    if (typeof bootstrap !== 'undefined' && bootstrap.Toast) {
        const toastElement = document.getElementById(toastId);
        const toast = new bootstrap.Toast(toastElement, {
            autohide: true,
            delay: 3000
        });
        toast.show();

        // Remove toast from DOM after it's hidden
        toastElement.addEventListener('hidden.bs.toast', function () {
            $(toastElement).remove();
        });
    }
}

// Animation for adding product to cart
function animateAddToCart($button) {
    // Create a copy of the product for animation
    const $productRow = $button.closest('tr');
    const productName = $productRow.find('td:first').text().trim();

    // Create flying element
    const $flyingElement = $('<div class="flying-item"></div>');
    $flyingElement.text(productName);
    $flyingElement.css({
        position: 'absolute',
        background: '#fff',
        padding: '5px 10px',
        borderRadius: '4px',
        boxShadow: '0 2px 5px rgba(0,0,0,0.2)',
        zIndex: 9999,
        width: 'auto',
        opacity: 0.9
    });

    // Append to body
    $('body').append($flyingElement);

    // Get positions
    const buttonOffset = $button.offset();
    const cartButtonOffset = $('a[href="CartURL?service=showCart"]').offset();

    // Position the flying element at the button
    $flyingElement.css({
        top: buttonOffset.top + 'px',
        left: buttonOffset.left + 'px'
    });

    // Animate the flying element to the cart
    $flyingElement.animate({
        top: cartButtonOffset.top,
        left: cartButtonOffset.left,
        opacity: 0.2,
        width: '10px',
        height: '10px'
    }, 800, 'easeOutQuad', function () {
        // Remove the flying element
        $flyingElement.remove();

        // Flash the cart button
        const $cartButton = $('a[href="CartURL?service=showCart"]');
        $cartButton.addClass('cart-highlight');
        setTimeout(function () {
            $cartButton.removeClass('cart-highlight');
        }, 700);
    });
} 