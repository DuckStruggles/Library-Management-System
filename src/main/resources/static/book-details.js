const BASE_URL = "http://localhost:8081/books";

const params =
    new URLSearchParams(window.location.search);

const bookId =
    params.get("id");

loadBook();

async function loadBook() {

    const response =
        await fetch(`${BASE_URL}/${bookId}`);

    const book =
        await response.json();

    const coverUrl =
        `https://covers.openlibrary.org/b/isbn/${book.isbn}-L.jpg`;

    document.getElementById("bookDetails").innerHTML = `

        <div class="details-card">

            <img
                src="${coverUrl}"
                onerror="this.src='images/no-cover.png'"
                class="details-cover">

            <div>

                <h1>${book.title}</h1>

                <h3>${book.author}</h3>

                <p><strong>ISBN:</strong> ${book.isbn}</p>

                <p><strong>Category:</strong> ${book.category}</p>

                <p><strong>Published:</strong> ${book.publishedYear}</p>

                <p>
                    <strong>Available:</strong>
                    ${book.availableCopies}
                    /
                    ${book.totalCopies}
                </p>

            </div>

        </div>
    `;
}