$(document).ready(function(){
            var next = 1;
            $(".add-more").click(function(e){
                e.preventDefault();
                var addto = "#fields" + next;
                var addRemove = "#fields" + (next);
                next = next + 1;
                var newIn = '<div id="fields'+ next +'"><div class="form-group"><div class="row"><div><div class="col-sm-6 form-group"><label for="medicine.title" class="control-label">Medicamento</label><div cssclass="form-control"><select id="prescripciones[].medicamento.nombreComercial" name="prescripciones[].medicamento.nombreComercial" class="form-control"><option value="NONE">--- Seleccione ---</option><option value="IBUPIRAC">IBUPIRAC</option><option value="IBU EVANOL" selected="selected">IBU EVANOL</option></select></div></div></div><div><div class="col-sm-6 form-group"><label for="observacion" class="control-label">Observaciones</label><span class="required">*</span><div cssclass="form-control"><input type="text" id="prescripciones[].descripcion" name="prescripciones[].descripcion" class="form-control"><label for="prescripciones[].descripcion" generated="true" class="error"></label></div></div></div></div></div></div>';
                var newInput = $(newIn);
                var removeBtn = '<button id="remove' + (next - 1) + '" class="btn btn-danger remove-me" >-</button></div><div id="fields'+ (next-1) +'">';
                var removeButton = $(removeBtn);
                $(addto).after(newInput);
                $(addRemove).after(removeButton);
                $("#fields" + next).attr('data-source',$(addto).attr('data-source'));
                $("#count").val(next);

                    $('.remove-me').click(function(e){
                        e.preventDefault();
                        var fieldNum = this.id.substr(this.id.lastIndexOf("e")+1);
                        var fieldID = "#fields" + fieldNum;
                        $(this).remove();
                        $(fieldID).remove();
                    });
            });
});