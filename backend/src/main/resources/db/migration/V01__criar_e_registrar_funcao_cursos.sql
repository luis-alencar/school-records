CREATE OR REPLACE FUNCTION "public"."fun_remove_acentos"(varchar)
  RETURNS "pg_catalog"."varchar" AS $BODY$
SELECT translate($1, 'áéíóúàèìòùãõâêîôôäëïöüçÁÉÍÓÚÀÈÌÒÙÃÕÂÊÎÔÛÄËÏÖÜÇ', 'aeiouaeiouaoaeiooaeioucAEIOUAEIOUAOAEIOOAEIOUC')
$BODY$
  LANGUAGE sql VOLATILE
  COST 100