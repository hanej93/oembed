async function fetchOEmbedData() {
    let urlParam = document.querySelector('.url-input').value;

    const url = `http://localhost:8080/oembed?url=${urlParam}`;
    try {
        const response = await fetch(url);
        const data = await response.json();

        if (!response.ok) {
            throw new Error(data.message);
        }

        renderOEmbedTitle(data);
        let object = createOEmbedField(data);
        renderHTMLTable(object);
    } catch (error) {
        console.error(error);
    }
}

function createOEmbedField(data) {
    let object = {};

    for (let key in data) {
        if (data.hasOwnProperty(key) && data[key] != null) {
            if (key == 'html') {
                let dimensions = `${data.width}` + `${data.height ? '/' + data.height : ''}`;
                let oEmbedField = `${key}<br/>(${dimensions})`;
                object[oEmbedField] = data[key];
            } else if (key == 'thumbnail_url') {
                let dimensions = `${data.thumbnail_width}` + `${data.thumbnail_height ? '/' + data.thumbnail_height : ''}`;
                let oEmbedField = `${key}<br/>(${dimensions})`;
                let _img = `<img src='${data[key]}'>`;
                object[oEmbedField] = _img;
            } else {
                object[key] = convertToLink(data[key]);
            }
        }
    }
    return object;
}

function convertToLink(text) {
    let urlRegex = /(https?:\/\/\S+)/g;
    return text.replace(urlRegex, function(url) {
        return '<a href="' + url + '">' + url + '</a>';
    });
}

function renderOEmbedTitle(data) {
    let _oembedTitle = document.querySelector('.oembed-title');
    _oembedTitle.innerHTML = '';

    let _titleHeader = document.createElement('div');
    _titleHeader.className = 'title-header';
    _titleHeader.textContent = 'title';
    _oembedTitle.appendChild(_titleHeader);

    let _titleName = document.createElement('div');
    _titleName.className = 'title-name';
    _titleName.textContent = data.title;
    _oembedTitle.appendChild(_titleName);
}

function renderHTMLTable(object) {
    let _table = document.querySelector('.oembed-table');
    _table.innerHTML = '';

    for (let key in object) {
        let _row = document.createElement('div');
        _row.className = 'oembed-row';

        let _th = document.createElement('div');
        _th.className = 'oembed-th';
        _th.innerHTML = key;
        _row.appendChild(_th);

        let _td = document.createElement('div');
        _td.className = 'oembed-td';
        _td.innerHTML = object[key];
        _row.appendChild(_td);

        _table.appendChild(_row);
    }
}