const BASE_URL = "http://localhost:8081/books";

const params =
new URLSearchParams(window.location.search);

const keyword =
params.get("keyword");

loadResults();

async function loadResults() {

```
try {

    const response =
        await fetch(
            `${BASE_URL}/search?keyword=${encodeURIComponent(keyword)}`
        );

    const data =
        await response.json();

    renderBooks(data.content);

} catch(error) {

    console.error(error);
}
```

}

function renderBooks(books) {

```
const booksContainer =
    document.getElementById("booksContainer");

booksContainer.innerHTML = "";

if (books.length === 0) {

    booksContainer.innerHTML =
        "<p>No books found.</p>";

    return;
}

books.forEach(book => {

    const coverUrl =
        `https://covers.openlibrary.org/b/isbn/${book.isbn}-M.jpg`;

    booksContainer.innerHTML += `

        <div
            class="book-card"
            onclick="viewBook(${book.id})">

            <img
                src="${coverUrl}"
                alt="${book.title}"
                class="book-cover"
                onerror="this.src='images/no-img.jpg'">

            <div class="book-info">

                <h3>${book.title}</h3>

                <p>
                    <strong>Author:</strong>
                    ${book.author}
                </p>

                <p>
                    <strong>Category:</strong>
                    ${book.category}
                </p>

                <p>
                    <strong>Available:</strong>
                    ${book.availableCopies}
                    /
                    ${book.totalCopies}
                </p>

            </div>

        </div>
    `;
});
```

}

function viewBook(id) {

```
window.location.href =
    `book-details.html?id=${id}`;
```

}
