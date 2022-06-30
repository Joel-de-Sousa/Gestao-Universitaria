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