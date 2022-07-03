--Júri
CREATE OR REPLACE FUNCTION fncobterjuriporid (
    p_cod_juri juris.cod_juri%TYPE
) RETURN SYS_REFCURSOR IS
    src_juris SYS_REFCURSOR;
BEGIN
    OPEN src_juris FOR SELECT cod_juri,cod_presidente,cod_orientador, cod_arguente
                           FROM juris
                          WHERE cod_juri = p_cod_juri;

    RETURN src_juris;
END;
/


CREATE OR REPLACE PROCEDURE prcobtertodosjuris (
	p_srcurjuris OUT SYS_REFCURSOR
) IS
BEGIN
	OPEN p_srcurjuris FOR SELECT cod_juri, cod_presidente, cod_orientador, cod_arguente
	                          FROM juris;

END;
/

create or replace  FUNCTION fncadicionarjuri (
     p_cod_presidente juris.cod_presidente%TYPE, p_cod_orientador juris.cod_orientador%TYPE,
    p_cod_arguente juris.cod_arguente%TYPE

) RETURN SYS_REFCURSOR IS
    cur_juri SYS_REFCURSOR;
BEGIN
    INSERT INTO juris (
        cod_presidente, cod_orientador, cod_arguente
    ) VALUES (
         p_cod_presidente, p_cod_orientador, p_cod_arguente
    );

    RETURN FNCOBTERJURIPORCODPRESIDENTE(p_cod_presidente);
END;
/


create or replace  FUNCTION fncobterjuriporCodPresidente (
    p_cod_Presidente juris.cod_Presidente%TYPE
) RETURN SYS_REFCURSOR IS
    src_juris SYS_REFCURSOR;
BEGIN
    OPEN src_juris FOR SELECT *
                           FROM juris
                          WHERE cod_Presidente = p_cod_Presidente;

    RETURN src_juris;
END;

CREATE OR REPLACE PROCEDURE prceliminarjuri (
	p_cod_juri juris.cod_juri%TYPE
) IS
BEGIN
	DELETE FROM juris
	 WHERE cod_juri = p_cod_juri;
END;
/


--Submissão

CREATE OR REPLACE FUNCTION fncobtersubmissaoporid (
	p_cod_submissao submissoes.cod_submissao%TYPE
) RETURN SYS_REFCURSOR IS
	src_submissoes SYS_REFCURSOR;
BEGIN
	OPEN src_submissoes FOR SELECT cod_submissao, titulo, url_ficheiro, linguagem_ficheiro, estado

	                       FROM submissoes
	                      WHERE cod_submissao = p_cod_submissao;

	RETURN src_medicos;
END;
/

CREATE OR REPLACE PROCEDURE prcobtertodassubmissoes (
	p_srcursubmissoes OUT SYS_REFCURSOR
) IS
BEGIN
	OPEN p_srcursubmissoes FOR SELECT cod_submissao, titulo, url_ficheiro, linguagem_ficheiro, estado

	                          FROM submissoes;
END;
/

CREATE OR REPLACE FUNCTION fncadicionarsubmissao (
	p_cod_submissao submissoes.cod_submissao%TYPE, p_titulo submissoes.titulo%TYPE, p_url_ficheiro submissoes.url_ficheiro%TYPE,
	p_linguagem_ficheiro submissoes.linguagem_ficheiro%TYPE, p_estado submissoes.estado%TYPE

) RETURN SYS_REFCURSOR IS
	cur_submissao SYS_REFCURSOR;
BEGIN
	INSERT INTO submissoes (
		cod_submissao, titulo, url_ficheiro, linguagem_ficheiro, estado
	) VALUES (
		p_cod_submissao, p_titulo, p_url_ficheiro, p_linguagem_ficheiro, p_estado
	);

	RETURN fncobtersubmissaoporid(p_cod_submissao);
END;
/

CREATE OR REPLACE PROCEDURE prceliminarsubmissao (
	p_cod_submissao submissoes.cod_submissao%TYPE
) IS
BEGIN
	DELETE FROM submissoes
	 WHERE cod_submissao = p_cod_submissao;
END;
/


--Convite

CREATE OR REPLACE FUNCTION fncobterconviteporid (
	p_cod_convite convites.cod_convite%TYPE
) RETURN SYS_REFCURSOR IS
	src_convites SYS_REFCURSOR;
BEGIN
	OPEN src_convites FOR SELECT cod_convite, cod_projeto , cod_estudante, cod_docente, estado

	                       FROM convites
	                      WHERE cod_convite = p_cod_convite;

	RETURN src_convites;
END;
/

CREATE OR REPLACE PROCEDURE prcobtertodosconvites (
	p_srcurconvites OUT SYS_REFCURSOR
) IS
BEGIN
	OPEN p_srcurconvites FOR SELECT cod_convite, cod_projeto , cod_estudante, cod_docente, estado

	                          FROM convites;
END;
/

CREATE OR REPLACE FUNCTION fncadicionarconvite (
	p_cod_convite convites.cod_convite%TYPE, p_cod_projeto convites.cod_projeto%TYPE, p_cod_estudante convites.cod_estudante%TYPE,
	p_cod_docente convites.cod_docente%TYPE, p_estado convites.estado%TYPE

) RETURN SYS_REFCURSOR IS
	cur_convite SYS_REFCURSOR;
BEGIN
	INSERT INTO convites (
		cod_convite, cod_projeto , cod_estudante, cod_docente, estado
	) VALUES (
		p_cod_convite, p_cod_projeto, p_cod_estudante, p_cod_docente, p_estado
	);

	RETURN fncobterconviteporid(p_cod_convite);
END;
/

CREATE OR REPLACE PROCEDURE prceliminarconvite (
	p_cod_convite convites.cod_convite%TYPE
) IS
BEGIN
	DELETE FROM convites
	 WHERE cod_convite = p_cod_convite;
END;
/

--Avaliacao
    --Preencher a nota e justificação de avaliação Presidente

CREATE OR REPLACE FUNCTION fncupdateavaliacao (
	p_cod_avaliacao  avaliacoes.cod_avaliacao %TYPE, p_nota avaliacoes.nota%TYPE, p_justificacao avaliacoes.justificacao%TYPE,
	p_data_avaliacao avaliacoes.data_avaliacao%TYPE

) RETURN SYS_REFCURSOR IS
	cur_avaliacao SYS_REFCURSOR;
BEGIN
	UPDATE avaliacoes
	SET    nota =p_nota, justificacao= p_justificacao, data_avaliacao=p_data_avaliacao
	WHERE p_cod_avaliacao=cod_avaliacao
	);

	RETURN fncobteravaliacaoporid(p_cod_avaliacao);
END;
/
    --Rever a avaliação, dando-a como concluída ou solicitando revisão

CREATE OR REPLACE FUNCTION fncupdateestadoavaliacaoruc (
	p_cod_avaliacao  avaliacoes.cod_avaliacao %TYPE, p_estado avaliacoes.estado%TYPE

) RETURN SYS_REFCURSOR IS
	cur_avaliacao SYS_REFCURSOR;
BEGIN
	UPDATE avaliacoes
	SET    estado=p_estado
	WHERE p_cod_avaliacao=cod_avaliacao
	);

	RETURN fncobteravaliacaoporid(p_cod_avaliacao);
END;
/

--Listar projetos que tenham sido propostos por determinada organização (1º getpropostasbynif + aceites 2º get projetos by cod proposta)

CREATE OR REPLACE FUNCTION fncobterprojetoporcodproposta (
	p_cod_proposta projetos.cod_proposta%TYPE
) RETURN SYS_REFCURSOR IS
	src_projetos SYS_REFCURSOR;
BEGIN
	OPEN src_projetos FOR SELECT cod_projeto, cod_proposta , cod_estudante, cod_orientador, estado

	                       FROM projetos
	                      WHERE cod_proposta = p_cod_proposta;

	RETURN src_projetos;
END;
/

 --Update estado do projeto para concluido

CREATE OR REPLACE FUNCTION fncupdateestadoprojetoconcluido (
	p_cod_projeto  projetos.cod_projeto %TYPE, p_estado projetos.estado%TYPE

) RETURN SYS_REFCURSOR IS
	cur_projeto SYS_REFCURSOR;
BEGIN
	UPDATE projetos
	SET    estado=p_estado
	WHERE p_cod_projeto=cod_projeto
	);

	RETURN fncobterprojetoporid(p_cod_projeto);
END;
/


--Obter Avaliacao por ID
create or replace function fncobteravaliacaoporid (
	p_codavaliacao avaliacoes.cod_avaliacao%type
) return SYS_REFCURSOR is
	src_avaliacoes SYS_REFCURSOR;
begin
	open src_avaliacoes for select *
	                       from avaliacoes
	                       where COD_AVALIACAO = p_codavaliacao;

	return src_avaliacoes;
end;
/

--Obter todas as avaliacoes--
create or replace procedure prcobtertodasavalicoes (
	p_srcuravaliacoes out SYS_REFCURSOR
) is
begin
	open p_srcuravaliacoes for select *
		                     from avaliacoes;

end;


--Save Avaliacao
create or replace function fncadicionaravaliacao (
	p_cod_avaliacao AVALIACOES.COD_AVALIACAO%type, p_cod_projeto AVALIACOES.COD_PROJETO%type,
	p_cod_MA AVALIACOES.COD_MA%type, p_cod_Juri AVALIACOES.COD_JURI%type, p_cod_Submissao AVALIACOES.COD_SUBMISSAO%type,
	p_nota AVALIACOES.NOTA%type, p_justificacao AVALIACOES.JUSTIFICACAO%type,
	p_data_Avaliacao AVALIACOES.DATA_AVALIACAO%type, p_estado AVALIACOES.ESTADO%type
) return SYS_REFCURSOR is
	cur_avaliacoes SYS_REFCURSOR;
begin
	insert into AVALIACOES (
		COD_AVALIACAO, COD_PROJETO, COD_MA, COD_JURI, COD_SUBMISSAO, nota, justificacao, DATA_AVALIACAO, estado
	) values (
		p_cod_Avaliacao, p_cod_projeto, p_cod_MA, p_cod_Juri, p_cod_Submissao, p_nota, p_justificacao, p_data_Avaliacao,
		p_estado
	);

	return FNCOBTERAVALIACAOPORID(p_cod_Avaliacao);
end;
/


--Save Avaliacao Criar avaliacao
create or replace function fncsaveavaliacao (
	p_cod_projeto AVALIACOES.COD_PROJETO%type,
	p_cod_MA AVALIACOES.COD_MA%type
) return SYS_REFCURSOR is
	cur_avaliacoes SYS_REFCURSOR;
begin
	insert into AVALIACOES (
		COD_PROJETO, COD_MA
	) values (
		p_cod_projeto, p_cod_MA
	);

	return prcobtertodasavaliacoescodprojeto(p_cod_projeto);
end;
/

--Elimina uma avaliacao
create or replace procedure prceliminaravaliacao (
	p_cod_Avaliacao AVALIACOES.cod_Avaliacao%type
) is
begin
	delete from AVALIACOES
	 where cod_Avaliacao = p_cod_Avaliacao;
end;
/


--Obter Projeto por ID
create or replace function fncobterprojetoporid (
	p_cod_projeto projetos.cod_projeto%type
) return SYS_REFCURSOR is
	src_projetos SYS_REFCURSOR;
begin
	open src_projetos for select *
	                       from projetos
	                       where COD_PROJETO = p_cod_projeto;

	return src_projetos;
end;
/

--Obter todos os projetos
create or replace procedure prcobtertodosprojetos (
	p_srcurprojetos out SYS_REFCURSOR
) is
begin
	open p_srcurprojetos for select *
		                     from projetos;

end;

--Save projeto
create or replace function fncadicionarprojeto (
	p_cod_Projeto projetos.cod_projeto%type, p_cod_Proposta projetos.cod_proposta%type,
	p_cod_Estudante projetos.cod_Estudante%type, p_cod_Orientador projetos.cod_Orientador%type
) return SYS_REFCURSOR is
	cur_projetos SYS_REFCURSOR;
begin
	insert into projetos (
		cod_projeto, cod_Proposta, cod_Estudante, cod_Orientador
	) values (
		p_cod_projeto, p_cod_Proposta, p_cod_Estudante, p_cod_Orientador
	);

	return FNCOBTERPROJETOPORID(p_cod_Projeto);
end;
/

--Elimina uma projeto
create or replace procedure prceliminarprojeto (
	p_cod_Projeto projetos.cod_Projeto%type
) is
begin
	delete from projetos
	 where cod_Projeto = p_cod_Projeto;
end;
/

--Obter ConviteRecusado por ID
create or replace function fncobterconviterecusadoporid (
	p_cod_Convite convitesrecusados.cod_Convite%type
) return SYS_REFCURSOR is
	src_convitesrecusados SYS_REFCURSOR;
begin
	open src_convitesrecusados for select *
	                       from CONVITESRECUSADOS
	                       where cod_Convite = p_cod_Convite;

	return src_convitesrecusados;
end;
/

--Obter todos os convites recusados
create or replace procedure prcobtertodosconvitesrecusados (
	p_srcurconvitesrecusados out SYS_REFCURSOR
) is
begin
	open p_srcurconvitesrecusados for select *
		                     from CONVITESRECUSADOS;

end;

--Save convite recusado
create or replace function fncadicionarconviterecusado (
	p_cod_Convite CONVITESRECUSADOS.cod_Convite%type, p_cod_Projeto CONVITESRECUSADOS.cod_Projeto%type,
	p_cod_Estudante CONVITESRECUSADOS.cod_Estudante%type, p_cod_Docente CONVITESRECUSADOS.cod_Docente%type,
	p_estado CONVITESRECUSADOS.estado%type
) return SYS_REFCURSOR is
	cur_convitesrecusados SYS_REFCURSOR;
begin
	insert into convitesrecusados (
		cod_Convite, cod_Projeto, cod_Estudante, cod_Docente, estado
	) values (
		p_cod_Convite, p_cod_Projeto, p_cod_Estudante, p_cod_Docente, p_estado
	);

	return FNCOBTERCONVITERECUSADOPORID(p_cod_Convite);
end;
/

--Elimina uma convite_recusado
create or replace procedure prceliminarconviterecusado (
	p_cod_Convite CONVITESRECUSADOS.cod_Convite%type
) is
begin
	delete from CONVITESRECUSADOS
	 where cod_Convite = p_cod_Convite;
end;
/

-- ## Listar Projeto que Incluiam Determinado Docente no Juri ## --
create or replace function fncobterprojetosporiddocente (
	p_cod_docente JURIS.COD_PRESIDENTE%type
) return SYS_REFCURSOR is
	src_avaliacoes SYS_REFCURSOR;
begin
	open src_avaliacoes for select avaliacoes.cod_projeto
                        from ((avaliacoes
                        inner join Projetos on avaliacoes.cod_projeto = projetos.cod_projeto)
                        inner join Juris on avaliacoes.cod_juri = juris.cod_juri)
                        where juris.cod_presidente = p_cod_docente
                        or juris.cod_orientador = p_cod_docente
                        or juris.cod_arguente = p_cod_docente;

	return src_avaliacoes;
end;
/

-- ## Listar Projetos que tenham um determinado momento de avaliação concluido ## --
create or replace function fncobterprojetosconcluidoporma (
	p_cod_MA JURIS.COD_PRESIDENTE%type
) return SYS_REFCURSOR is
	src_avaliacoes SYS_REFCURSOR;
begin
	open src_avaliacoes for select avaliacoes.cod_projeto
                        from (avaliacoes
                        inner join Projetos on avaliacoes.cod_projeto = projetos.cod_projeto)
                        where p_cod_MA = AVALIACOES.cod_MA and AVALIACOES.estado = 'CONCLUIDA';

    return src_avaliacoes;
end;
/

-- ## Listar todos os projetos que têm avaliacao concluida ## --
create or replace procedure prccobterprojetosconcluidos (
	p_srcurprojetos out SYS_REFCURSOR
)  is
begin
	open p_srcurprojetos for select projetos.cod_projeto
                        from PROJETOS
                        where PROJETOS.estado = 'CONCLUIDA';

end;
/

-- ## Incluam determinado momento de avaliação ocorrido entre duas datas ## --
create or replace function fncobtermaentredatas (
	p_data_avaliacao1 AVALIACOES.DATA_AVALIACAO%type,
	p_data_avaliacao2 AVALIACOES.DATA_AVALIACAO%type

) return SYS_REFCURSOR is
	src_ma SYS_REFCURSOR;
begin
	open src_ma for select cod_MA
                        from avaliacoes
                        where data_avaliacao
                        between p_data_avaliacao1
                        and p_data_avaliacao2;
    return src_ma;
end;
/

-- ## Obter Projeto por codigo Estudante ## --
create or replace function fncobterprojetoporcodestudante(
	p_cod_estudante projetos.cod_estudante%type
) return SYS_REFCURSOR is
	src_projetos SYS_REFCURSOR;
begin
	open src_projetos for select cod_projeto
	                       from projetos
	                       where cod_estudante = p_cod_estudante;

	return src_projetos;
end;
/

-- ## Obter Avaliacao por codigo Projeto ## --
create or replace function fncobteravaliacaoporcodigoprojeto(
	p_cod_projeto avaliacao.cod_projeto%type
) return SYS_REFCURSOR is
	src_avaliacoes SYS_REFCURSOR;
begin
	open src_avaliacoes for select cod_avaliacao
	                       from avaliacoes
	                       where cod_projeto = p_cod_projeto;

	return src_avaliacoes;
end;
/


