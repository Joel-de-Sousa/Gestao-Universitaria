package WSEdicao.domain.factories;


import WSEdicao.domain.entities.Uc;

public interface IUcFactory {

    public Uc createUc(int codUc, String sSigla, String sDenominacao);
}
