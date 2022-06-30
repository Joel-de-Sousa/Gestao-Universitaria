create or replace function FNCOBTERAVALIACAOPORID (
	p_codavaliacao avaliacao.codavaliacao%TYPE
) RETURN SYS_REFCURSOR IS
	src_avaliacoes SYS_REFCURSOR;
BEGIN
	OPEN src_avaliacoes FOR SELECT *
	                       FROM avaliacao
	                       WHERE CODAVALIACAO = p_codavaliacao;

	RETURN src_avaliacoes;
END;
/
