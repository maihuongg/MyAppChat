const getTokenRequestUser1 = {
  method: 'POST',
  url: 'http://localhost:8000/user/login',
  header: {'Content-Type': 'application/json'},
  body: {
    'mode': 'raw',
    'raw': JSON.stringify({
        email: "admin@gmail.com",
        password: "admin",
    })
  }
};

pm.sendRequest(getTokenRequestUser1, function (err, response) {
    const data = response.json();
    pm.globals.set("TOKEN_USER1", data.access)
    console.log('ok', data);
});

const getTokenRequestUser2 = {
  method: 'POST',
  url: 'http://localhost:8000/user/login',
  header: {'Content-Type': 'application/json'},
  body: {
    'mode': 'raw',
    'raw': JSON.stringify({
        email: "admin2@gmail.com",
        password: "admin",
    })
  }
};

pm.sendRequest(getTokenRequestUser2, function (err, response) {
    const data = response.json();
    pm.globals.set("TOKEN_USER2", data.access)
    console.log('ok', data);
});

const getTokenRequestUser3 = {
  method: 'POST',
  url: 'http://localhost:8000/user/login',
  header: {'Content-Type': 'application/json'},
  body: {
    'mode': 'raw',
    'raw': JSON.stringify({
        email: "admin3@gmail.com",
        password: "admin",
    })
  }
};

pm.sendRequest(getTokenRequestUser3, function (err, response) {
    const data = response.json();
    pm.globals.set("TOKEN_USER3", data.access)
    console.log('ok', data);
});

const getTokenRequestUser4 = {
  method: 'POST',
  url: 'http://localhost:8000/user/login',
  header: {'Content-Type': 'application/json'},
  body: {
    'mode': 'raw',
    'raw': JSON.stringify({
        email: "admin4@gmail.com",
        password: "admin",
    })
  }
};

pm.sendRequest(getTokenRequestUser4, function (err, response) {
    const data = response.json();
    pm.globals.set("TOKEN_USER4", data.access)
    console.log('ok', data);
});

const getTokenRequestUser5 = {
  method: 'POST',
  url: 'http://localhost:8000/user/login',
  header: {'Content-Type': 'application/json'},
  body: {
    'mode': 'raw',
    'raw': JSON.stringify({
        email: "admin5@gmail.com",
        password: "admin",
    })
  }
};

pm.sendRequest(getTokenRequestUser5, function (err, response) {
    const data = response.json();
    pm.globals.set("TOKEN_USER5", data.access)
    console.log('ok', data);
});
