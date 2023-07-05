function fetchOEmbedData() {
    let urlParam = document.querySelector('.url-input').value;

    const url = 'http://localhost:8080/oembed?url=' + urlParam;
    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            let object = {};

            for (let key in data) {
                if(data.hasOwnProperty(key) && data[key] != null) {
                    if (key == 'html') {
                        let embedField = `${key}<br/>(${data.width}` + (data.height ? `/${data.height})` : ')');
                        object[embedField] = data[key];
                    } else if (key == 'thumbnail_url') {
                        let embedField = `${key}<br/>(${data.thumbnail_width}` + (data.thumbnail_height ? `/${data.thumbnail_height})` : ')');
                        let _img = `<img src='${data[key]}'>`;
                        object[embedField] = _img;
                    } else {
                        object[key] = convertToLink(data[key]);
                    }
                }
            }

            let _table = '';
            for (let key in object) {
                _table += '<div class="oembed-row">';
                _table += '<div class="oembed-th">' + key + '</div>';
                _table += '<div class="oembed-td">' + object[key] + '</div>';
                _table += '</div>';
            }

            document.querySelector(".oembed-table").innerHTML = _table;

            console.log(JSON.stringify(object));
            document.getElementById('response').textContent = JSON.stringify(data, null, 2);
        })
        .catch(error => {
            console.error('There has been a problem with your fetch operation:', error);
        });
}

function convertToLink(text) {
    let urlRegex = /(https?:\/\/[^\s]+)/g;
    return text.replace(urlRegex, function(url) {
        return '<a href="' + url + '">' + url + '</a>';
    });
}