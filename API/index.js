const { spawn } = require('child_process');
const express = require('express')
const app = express();
app.use(express.json());


app.get('/exeScript', async (req, res) => {
    child = spawn('powershell.exe', ["C:\User\jdimont\scripts\script.ps1"])
    res.status(200).send('Executed')
})

//create the express server
var server = app.listen(3000, '<<Ton_Ip>>', (req, res) => {
    var host = server.address().address
    var port = server.address().port
    console.log(`Server running at http://${host}:${port}/ ......`)
})