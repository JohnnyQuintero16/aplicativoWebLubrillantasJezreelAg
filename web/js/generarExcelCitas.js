function datenum(v, date1904) {
    if (date1904)
        v += 1462;
    var epoch = Date.parse(v);
    return (epoch - new Date(Date.UTC(1899, 11, 30))) / (24 * 60 * 60 * 1000);
}

function sheet_from_array_of_arrays(data, opts) {
    var ws = {};
    var range = {s: {c: 10000000, r: 10000000}, e: {c: 0, r: 0}};
    for (var R = 0; R != data.length; ++R) {
        for (var C = 0; C != data[R].length; ++C) {
            if (range.s.r > R)
                range.s.r = R;
            if (range.s.c > C)
                range.s.c = C;
            if (range.e.r < R)
                range.e.r = R;
            if (range.e.c < C)
                range.e.c = C;
            var cell = {v: data[R][C]};
            if (cell.v == null)
                continue;
            var cell_ref = XLSX.utils.encode_cell({c: C, r: R});


            if (typeof cell.v === 'number')
                cell.t = 'n';
            else if (typeof cell.v === 'boolean')
                cell.t = 'b';
            else if (cell.v instanceof Date) {
                cell.t = 'n';
                cell.z = XLSX.SSF._table[14];
                cell.v = datenum(cell.v);
            } else
                cell.t = 's';



            cell.s = {
                border: {
                    top: {style: "thin", color: "000000"},
                    bottom: {style: "thin", color: "000000"},
                    left: {style: "thin", color: "000000"},
                    right: {style: "thin", color: "000000"}
                }
            };



            if (R == 0) {
                cell.s = {
                    fill: {
                        fgColor: {rgb: "4044BC"}
                    },
                    font: {
                        color: {rgb: "FFFFFF"}

                    }
                }
            }

            ws[cell_ref] = cell;
        }

    }

    if (range.s.c < 10000000)
        ws['!ref'] = XLSX.utils.encode_range(range);
    return ws;
}


function Workbook() {
    if (!(this instanceof Workbook))
        return new Workbook();
    this.SheetNames = [];
    this.Sheets = {};
}


function s2ab(s) {
    var buf = new ArrayBuffer(s.length);
    var view = new Uint8Array(buf);
    for (var i = 0; i != s.length; ++i)
        view[i] = s.charCodeAt(i) & 0xFF;
    return buf;
}

function save(datos, nombre) {
    /* original data */
    var data = datos;
    console.log(data.toString());
    var ws_name = nombre;

    var wb = new Workbook(), ws = sheet_from_array_of_arrays(data);
    var wscols = [
    {wch:6},
    {wch:13},
    {wch:15},
    {wch:18},
    {wch:13},
    {wch:30},
    {wch:60},
    {wch:10},
    {wch:6},
    {wch:12}
];

ws['!cols'] = wscols;
    /* add worksheet to workbook */
    wb.SheetNames.push(ws_name);
    wb.Sheets[ws_name] = ws;
    
    var wbout = XLSX.write(wb, {bookType: 'xlsx', bookSST: true, type: 'binary'});

    saveAs(new Blob([s2ab(wbout)], {type: "application/octet-stream"}), nombre + ".xlsx");
}

function desabilitar() {

                var inicial = document.querySelector('#inicial');
                var final = document.querySelector('#final');
                
                    final.removeAttribute("readonly");

                
            }
            function limitarFecha() {

                var inicial = document.querySelector('#inicial');
                var final = document.querySelector('#final');
                console.log(inicial.value);
                if (inicial.value!=="") {
                    
                    final.setAttribute("min", inicial.value);
                } else {
                    final.setAttribute("readonly","false");

                }
            }
