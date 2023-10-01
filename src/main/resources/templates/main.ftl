<#import "parts/common.ftl" as c>

<@c.page>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/main" class="form-inline">
                <input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Search by consignee">
                <button type="submit" class="btn btn-primary ml-2">Search</button>
            </form>
        </div>
    </div>

    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
        Add new Cargo
    </a>
    <div class="collapse" id="collapseExample">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <input type="text" class="form-control" name="text" placeholder="Введите сообщение" />
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="shipper" required placeholder="Грузоотправитель">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="consignee" required placeholder="Грузополучатель">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="consignment_note" required placeholder="Номер накладной">
                </div>
                <div class="form-group">
                    <input type="number" step="0.001" class="form-control" name="weight" required placeholder="Вес">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="number_of_packages" required placeholder="Количество мест">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="name" required placeholder="Наименование товара">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="price" required placeholder="Стоимость">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="hs_code" required placeholder="КОД ТН ВЭД">
                </div>
                <div class="form-group">
                    <input type="datetime-local" class="form-control" name="date" required placeholder="Время и дата">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="truck_plate" required placeholder="Номер ТС">
                </div>
                <div class="form-group">
                    <div class="custom-file">
                        <input type="file" name="file" id="customFile">
                        <label class="custom-file-label" for="customFile">Choose file</label>
                    </div>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Добавить</button>
                </div>
            </form>
        </div>
    </div>

    <table class="table" style="margin-top: 10px">
        <thead class="thead-dark">
            <tr>
                <th scope="col">Сообщение</th>
                <th scope="col">Грузоотправитель</th>
                <th scope="col">Грузополучатель</th>
                <th scope="col">Номер накладной</th>
                <th scope="col">Вес</th>
                <th scope="col">Количество мест</th>
                <th scope="col">Наименование товара</th>
                <th scope="col">Стоимость</th>
                <th scope="col">Код ТН ВЭД</th>
                <th scope="col">Дата</th>
                <th scope="col">Номер ТС</th>
                <th scope="col">Файл</th>
                <th scope="col">Свойства</th>

            </tr>
        </thead>


        <#list cargos as cargo>
            <tr>
                <#if cargo.text??>
                    <td>${cargo.text}</td>
                </#if>
                <td>${cargo.shipper}</td>
                <td>${cargo.consignee}</td>
                <td>${cargo.consignment_note}</td>
                <td>${cargo.weight}</td>
                <td>${cargo.number_of_packages}</td>
                <td>${cargo.name}</td>
                <td>${cargo.price}</td>
                <td>${cargo.hs_code}</td>
                <td>${cargo.date}</td>
                <td>${cargo.truck_plate}</td>



                <#if cargo.filename??>
                    <td>${cargo.filename}</td>
                <#else>
                    <td>-</td>
                </#if>

                <td>
                    <form method="post"  enctype="multipart/form-data">
                        <input type="hidden" name="id" value="${cargo.id}">
                        <input type="hidden" name="_csrf" value="${_csrf.token}" />
                        <button type="submit" style="color: red" >X</button>
                    </form>
                    <div style="color: blue">R</div>
                </td>

            </tr>

        </#list>
    </table>
</@c.page>