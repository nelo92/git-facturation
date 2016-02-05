package fr.ma.facturation.utils;

import java.util.StringTokenizer;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringEscapeUtils;

/**
 * Classe utilitaire de manipulation de chaÃ®ne de caractÃ¨res <br>
 * Cette classe hÃ©rite de la classe StringUtils de la librairie commons-lang.
 */
public class StringUtils extends org.apache.commons.lang.StringUtils {

    /**
     * Supprime tous les espaces prÃ©sents au dÃ©but d'une chaÃ®ne de caractÃ¨res donnÃ©e
     * @param str chaÃ®ne de caractÃ¨res Ã  transformer
     * @return clone de la chaÃ®ne de caractÃ¨res donnÃ©e en entrÃ©e aprÃ¨s transformation
     */
    public static String ltrim(String source) {
        return source != null ? source.replaceAll("^\\s+", "") : null;
    }

    /**
     * Supprime tous les espaces prÃ©sents en fin d'une chaÃ®ne de caractÃ¨res donnÃ©e
     * @param str chaÃ®ne de caractÃ¨res Ã  transformer
     * @return clone de la chaÃ®ne de caractÃ¨res donnÃ©e en entrÃ©e aprÃ¨s transformation
     */
    public static String rtrim(String source) {
        return source != null ? source.replaceAll("\\s+$", "") : null;
    }

    /**
     * Remplace tous les blocs d'espaces prÃ©sent au milieu d'une chaÃ®ne de caractÃ¨res donnÃ©e par un unique espace
     * @param str chaÃ®ne de caractÃ¨res Ã  transformer
     * @return clone de la chaÃ®ne de caractÃ¨res donnÃ©e en entrÃ©e aprÃ¨s transformation
     */
    public static String itrim(String source) {
        return source != null ? source.replaceAll("\\b\\s{2,}\\b", " ").replaceAll("\\b\\s{2,}\\B", "").replaceAll("\\B\\s{2,}\\b", " ").replaceAll("\\s+", " ") : null;
    }

    /**
     * Supprime tous les espaces initules dans une chaÃ®ne de caractÃ¨res donnÃ©e
     * @param str chaÃ®ne de caractÃ¨res Ã  transformer
     * @return clone de la chaÃ®ne de caractÃ¨res donnÃ©e en entrÃ©e aprÃ¨s transformation
     */
    public static String trim(String source) {
        return source != null ? itrim(source.trim()) : null;
    }

    /**
     * Tronque une chaine de caractÃ¨re du 1er caractÃ¨re Ã  la longueur voulue
     * @param source chaÃ®ne de caractÃ¨res Ã  transformer
     * @param length longueur finale de la chaÃ®ne
     * @return clone de la chaÃ®ne de caractÃ¨res donnÃ©e en entrÃ©e aprÃ¨s transformation
     */
    public static String truncate(String source, int length) {
        return trim(StringUtils.abbreviate(source, length));
    }
    /**
     * Tronque une chaine de caractÃ¨re du 1er caractÃ¨re Ã  la longueur voulue
     * @param source chaÃ®ne de caractÃ¨res Ã  transformer
     * @param length longueur finale de la chaÃ®ne
     * @return clone de la chaÃ®ne de caractÃ¨res donnÃ©e en entrÃ©e aprÃ¨s transformation
     */
    public static String capitalize(String source) {
        return trim(org.apache.commons.lang.StringUtils.capitalize(source.toLowerCase()));
    }
    /**
     * Ajout espace chaque nb carateres pour affichage sur plusiers lignes
     * @param libelle
     * @param nb
     * @return
     */
    public static String addSpace(String libelle, int nb) {
        if (libelle != null) {
            StringBuffer buffer = new StringBuffer("");
            StringTokenizer lib = new StringTokenizer(libelle, " ", true);
            while (lib.hasMoreTokens()) {
                String token = lib.nextToken();
                if (token.length() > nb)
                    token = token.substring(0, nb) + " " + addSpace(token.substring(nb, token.length()), nb);
                buffer.append(token);
            }
            return buffer.toString();
        }

        return libelle;
    }

    /**
     * Renvoie la chaine donnÃ©e entre quotes, ou NULL si la chaine est nulle ou composÃ©e uniquement d'espaces.
     */
    public static String escapeSqlString(Object o) {
        if (o == null)
            return null;
        String str = o.toString().trim();
        if (StringUtils.isNotBlank(str))
            return trim(StringUtils.replace(StringEscapeUtils.escapeSql(str), "(PROMO)", ""));
        return null;
    }
    /**
     * Renvoie la chaine donnÃ©e entre quotes, ou NULL si la chaine est nulle ou composÃ©e uniquement d'espaces.
     */
    public static String escapeJavaScript(Object o) {
        if (o == null)
            return null;
        String str = o.toString().trim();
        if (StringUtils.isNotBlank(str))
            return trim(org.apache.commons.lang.StringEscapeUtils.escapeJavaScript(str));
        return null;
    }

    public static String escapeHtml(Object o) {
        if (o == null)
            return null;
        String str = o.toString().trim();
        if (StringUtils.isNotBlank(str))
            return trim(org.apache.commons.lang.StringEscapeUtils.escapeHtml(str.replaceAll("Â¿U", "Å’U")));
        return null;
    }
    /**
     * Renvoie la chaine donnÃ©e entre quotes, ou NULL si la chaine est nulle ou composÃ©e uniquement d'espaces.
     */
    public static String SlashSqlString(Object o) {
        if (o == null)
            return null;
        String st = o.toString().trim();
        if (StringUtils.isNotBlank(st))
            return trim(replace(st, "/", ""));
        return null;
    }
    /**
     * Renvoie la chaine donnÃ©e sans punctuation.
     */
    public static String escapeInvalidcharacters(String str) {
        Pattern escaper = Pattern.compile("([^a-zA-z0-9])");
        return trim(escaper.matcher(str).replaceAll(" "));
    }

    public static String escapeInvalidRegexSyntax(String str) {
    	return trim(str.replaceAll("\\*", "").replaceAll("\\.", "").replaceAll("\\?", "").replaceAll("\\+","").replaceAll("\\(","").replaceAll("\\)",""));
    }

    public static String getNumeric(String value){
        return trim(value.replaceAll("[^\\d]", ""));
    }
    /**
     * Remplace tous les caractÃ¨res accentuÃ©s par leur Ã©quivalent sans accent
     * @param a_sChaine une chaine
     * @return la mÃªme chaine sans les accents
     */
//    public static String sansAccent(String a_sChaine) {
//        a_sChaine = a_sChaine.replace('Ã ', 'a');
//        a_sChaine = a_sChaine.replace('Ã¢', 'a');
//        a_sChaine = a_sChaine.replace('Ã£', 'a');
//        a_sChaine = a_sChaine.replace('Ã¤', 'a');
//        a_sChaine = a_sChaine.replace('Ã¥', 'a');
//        a_sChaine = a_sChaine.replace('Ã§', 'c');
//        a_sChaine = a_sChaine.replace('Ã¨', 'e');
//        a_sChaine = a_sChaine.replace('Ã©', 'e');
//        a_sChaine = a_sChaine.replace('Ãª', 'e');
//        a_sChaine = a_sChaine.replace('Ã«', 'e');
//        a_sChaine = a_sChaine.replace('Ã¬', 'i');
//        a_sChaine = a_sChaine.replace('Ã­', 'i');
//        a_sChaine = a_sChaine.replace('Ã®', 'i');
//        a_sChaine = a_sChaine.replace('Ã¯', 'i');
//        a_sChaine = a_sChaine.replace('Ã±', 'n');
//        a_sChaine = a_sChaine.replace('Ã²', 'o');
//        a_sChaine = a_sChaine.replace('Ã³', 'o');
//        a_sChaine = a_sChaine.replace('Ã´', 'o');
//        a_sChaine = a_sChaine.replace('Ãµ', 'o');
//        a_sChaine = a_sChaine.replace('Ã¶', 'o');
//        a_sChaine = a_sChaine.replace('Ã¸', 'o');
//        a_sChaine = a_sChaine.replace('Ã¹', 'u');
//        a_sChaine = a_sChaine.replace('Ãº', 'u');
//        a_sChaine = a_sChaine.replace('Ã»', 'u');
//        a_sChaine = a_sChaine.replace('Ã¼', 'u');
//        a_sChaine = a_sChaine.replace('Ã½', 'y');
//        a_sChaine = a_sChaine.replace('Ã¿', 'y');
//        a_sChaine = a_sChaine.replace('Ã€', 'A');
//        a_sChaine = a_sChaine.replace('Ã‚', 'A');
//        a_sChaine = a_sChaine.replace('Ã„', 'A');
//        a_sChaine = a_sChaine.replace('Ã…', 'A');
//        a_sChaine = a_sChaine.replace('Ãƒ', 'A');
//        a_sChaine = a_sChaine.replace('Ã‰', 'E');
//        a_sChaine = a_sChaine.replace('Ãˆ', 'E');
//        a_sChaine = a_sChaine.replace('ÃŠ', 'E');
//        a_sChaine = a_sChaine.replace('Ã‹', 'E');
//        a_sChaine = a_sChaine.replace('Ã�', 'I');
//        a_sChaine = a_sChaine.replace('ÃŒ', 'I');
//        a_sChaine = a_sChaine.replace('Ã�', 'I');
//        a_sChaine = a_sChaine.replace('ÃŽ', 'I');
//        a_sChaine = a_sChaine.replace('Ã“', 'O');
//        a_sChaine = a_sChaine.replace('Ã’', 'O');
//        a_sChaine = a_sChaine.replace('Ã”', 'O');
//        a_sChaine = a_sChaine.replace('Ã–', 'O');
//        a_sChaine = a_sChaine.replace('Ã˜', 'O');
//        a_sChaine = a_sChaine.replace('Ã•', 'O');
//        a_sChaine = a_sChaine.replace('Ãš', 'U');
//        a_sChaine = a_sChaine.replace('Ã™', 'U');
//        a_sChaine = a_sChaine.replace('Ã›', 'U');
//        a_sChaine = a_sChaine.replace('Ãœ', 'U');
//        a_sChaine = a_sChaine.replace('Ã‡', 'C');
//        a_sChaine = a_sChaine.replace('Ã‘', 'N');
//        a_sChaine = a_sChaine.replace('Ã�', 'Y');
//        a_sChaine = a_sChaine.replace('ÂŸ', 'Y');
//        return a_sChaine;
//    }


    public static String sansAccent(String chaine){

    	chaine=chaine.replaceAll("Å“","oe");
    	chaine=chaine.replaceAll("Å’","OE");
    	chaine=chaine.replaceAll("Ã¦","ae");
    	chaine=chaine.replaceAll("Ã†","AE");

    	chaine=chaine.replaceAll("Ã€" , "A");
    	chaine=chaine.replaceAll("Ã�" , "A");
    	chaine=chaine.replaceAll("Ã‚" , "A");
    	chaine=chaine.replaceAll("Ãƒ" , "A");
    	chaine=chaine.replaceAll("Ã„" , "A");
    	chaine=chaine.replaceAll("Ã…" , "A");
    	chaine=chaine.replaceAll("&#256;" , "A");
    	chaine=chaine.replaceAll("&#258;" , "A");
    	chaine=chaine.replaceAll("&#461;" , "A");
    	chaine=chaine.replaceAll("&#7840;" , "A");
    	chaine=chaine.replaceAll("&#7842;" , "A");
    	chaine=chaine.replaceAll("&#7844;" , "A");
    	chaine=chaine.replaceAll("&#7846;" , "A");
    	chaine=chaine.replaceAll("&#7848;" , "A");
    	chaine=chaine.replaceAll("&#7850;" , "A");
    	chaine=chaine.replaceAll("&#7852;" , "A");
    	chaine=chaine.replaceAll("&#7854;" , "A");
    	chaine=chaine.replaceAll("&#7856;" , "A");
    	chaine=chaine.replaceAll("&#7858;" , "A");
    	chaine=chaine.replaceAll("&#7860;" , "A");
    	chaine=chaine.replaceAll("&#7862;" , "A");
    	chaine=chaine.replaceAll("&#506;" , "A");
    	chaine=chaine.replaceAll("&#260;" , "A");

    	chaine=chaine.replaceAll("Ã " , "a");
    	chaine=chaine.replaceAll("Ã¡" , "a");
    	chaine=chaine.replaceAll("Ã¢" , "a");
    	chaine=chaine.replaceAll("Ã£" , "a");
    	chaine=chaine.replaceAll("Ã¤" , "a");
    	chaine=chaine.replaceAll("Ã¥" , "a");
    	chaine=chaine.replaceAll("&#257;" , "a");
    	chaine=chaine.replaceAll("&#259;" , "a");
    	chaine=chaine.replaceAll("&#462;" , "a");
    	chaine=chaine.replaceAll("&#7841;" , "a");
    	chaine=chaine.replaceAll("&#7843;" , "a");
    	chaine=chaine.replaceAll("&#7845;" , "a");
    	chaine=chaine.replaceAll("&#7847;" , "a");
    	chaine=chaine.replaceAll("&#7849;" , "a");
    	chaine=chaine.replaceAll("&#7851;" , "a");
    	chaine=chaine.replaceAll("&#7853;" , "a");
    	chaine=chaine.replaceAll("&#7855;" , "a");
    	chaine=chaine.replaceAll("&#7857;" , "a");
    	chaine=chaine.replaceAll("&#7859;" , "a");
    	chaine=chaine.replaceAll("&#7861;" , "a");
    	chaine=chaine.replaceAll("&#7863;" , "a");
    	chaine=chaine.replaceAll("&#507;" , "a");
    	chaine=chaine.replaceAll("&#261;" , "a");

    	chaine=chaine.replaceAll("Ã‡" , "C");
    	chaine=chaine.replaceAll("&#262;" , "C");
    	chaine=chaine.replaceAll("&#264;" , "C");
    	chaine=chaine.replaceAll("&#266;" , "C");
    	chaine=chaine.replaceAll("&#268;" , "C");

    	chaine=chaine.replaceAll("Ã§" , "c");
    	chaine=chaine.replaceAll("&#263;" , "c");
    	chaine=chaine.replaceAll("&#265;" , "c");
    	chaine=chaine.replaceAll("&#267;" , "c");
    	chaine=chaine.replaceAll("&#269;" , "c");

    	chaine=chaine.replaceAll("Ã�" , "D");
    	chaine=chaine.replaceAll("&#270;" , "D");
    	chaine=chaine.replaceAll("&#272;" , "D");
    	chaine=chaine.replaceAll("&#271;" , "d");
    	chaine=chaine.replaceAll("&#273;" , "d");

    	chaine=chaine.replaceAll("Ãˆ" , "E");
    	chaine=chaine.replaceAll("Ã‰" , "E");
    	chaine=chaine.replaceAll("ÃŠ" , "E");
    	chaine=chaine.replaceAll("Ã‹" , "E");
    	chaine=chaine.replaceAll("&#274;" , "E");
    	chaine=chaine.replaceAll("&#276;" , "E");
    	chaine=chaine.replaceAll("&#278;" , "E");
    	chaine=chaine.replaceAll("&#280;" , "E");
    	chaine=chaine.replaceAll("&#282;" , "E");
    	chaine=chaine.replaceAll("&#7864;" , "E");
    	chaine=chaine.replaceAll("&#7866;" , "E");
    	chaine=chaine.replaceAll("&#7868;" , "E");
    	chaine=chaine.replaceAll("&#7870;" , "E");
    	chaine=chaine.replaceAll("&#7872;" , "E");
    	chaine=chaine.replaceAll("&#7874;" , "E");
    	chaine=chaine.replaceAll("&#7876;" , "E");
    	chaine=chaine.replaceAll("&#7878;" , "E");

    	chaine=chaine.replaceAll("Ã¨" , "e");
    	chaine=chaine.replaceAll("Ã©" , "e");
    	chaine=chaine.replaceAll("Ãª" , "e");
    	chaine=chaine.replaceAll("Ã«" , "e");
    	chaine=chaine.replaceAll("&#275;" , "e");
    	chaine=chaine.replaceAll("&#277;" , "e");
    	chaine=chaine.replaceAll("&#279;" , "e");
    	chaine=chaine.replaceAll("&#281;" , "e");
    	chaine=chaine.replaceAll("&#283;" , "e");
    	chaine=chaine.replaceAll("&#7865;" , "e");
    	chaine=chaine.replaceAll("&#7867;" , "e");
    	chaine=chaine.replaceAll("&#7869;" , "e");
    	chaine=chaine.replaceAll("&#7871;" , "e");
    	chaine=chaine.replaceAll("&#7873;" , "e");
    	chaine=chaine.replaceAll("&#7875;" , "e");
    	chaine=chaine.replaceAll("&#7877;" , "e");
    	chaine=chaine.replaceAll("&#7879;" , "e");

    	chaine=chaine.replaceAll("&#284;" , "G");
    	chaine=chaine.replaceAll("&#286;" , "G");
    	chaine=chaine.replaceAll("&#288;" , "G");
    	chaine=chaine.replaceAll("&#290;" , "G");

    	chaine=chaine.replaceAll("&#285;" , "g");
    	chaine=chaine.replaceAll("&#287;" , "g");
    	chaine=chaine.replaceAll("&#289;" , "g");
    	chaine=chaine.replaceAll("&#291;" , "g");

    	chaine=chaine.replaceAll("&#292;" , "H");
    	chaine=chaine.replaceAll("&#294;" , "H");

    	chaine=chaine.replaceAll("&#293;" , "h");
    	chaine=chaine.replaceAll("&#295;" , "h");

    	chaine=chaine.replaceAll("ÃŒ" , "I");
    	chaine=chaine.replaceAll("Ã�" , "I");
    	chaine=chaine.replaceAll("ÃŽ" , "I");
    	chaine=chaine.replaceAll("Ã�" , "I");
    	chaine=chaine.replaceAll("&#296;" , "I");
    	chaine=chaine.replaceAll("&#298;" , "I");
    	chaine=chaine.replaceAll("&#300;" , "I");
    	chaine=chaine.replaceAll("&#302;" , "I");
    	chaine=chaine.replaceAll("&#304;" , "I");
    	chaine=chaine.replaceAll("&#463;" , "I");
    	chaine=chaine.replaceAll("&#7880;" , "I");
    	chaine=chaine.replaceAll("&#7882;" , "I");

    	chaine=chaine.replaceAll("&#308;" , "J");

    	chaine=chaine.replaceAll("&#309;" , "j");

    	chaine=chaine.replaceAll("&#310;" , "K");

    	chaine=chaine.replaceAll("&#311;" , "k");

    	chaine=chaine.replaceAll("&#313;" , "L");
    	chaine=chaine.replaceAll("&#315;" , "L");
    	chaine=chaine.replaceAll("&#317;" , "L");
    	chaine=chaine.replaceAll("&#319;" , "L");
    	chaine=chaine.replaceAll("&#321;" , "L");

    	chaine=chaine.replaceAll("&#314;" , "l");
    	chaine=chaine.replaceAll("&#316;" , "l");
    	chaine=chaine.replaceAll("&#318;" , "l");
    	chaine=chaine.replaceAll("&#320;" , "l");
    	chaine=chaine.replaceAll("&#322;" , "l");

    	chaine=chaine.replaceAll("Ã‘" , "N");
    	chaine=chaine.replaceAll("&#323;" , "N");
    	chaine=chaine.replaceAll("&#325;" , "N");
    	chaine=chaine.replaceAll("&#327;" , "N");

    	chaine=chaine.replaceAll("Ã±" , "n");
    	chaine=chaine.replaceAll("&#324;" , "n");
    	chaine=chaine.replaceAll("&#326;" , "n");
    	chaine=chaine.replaceAll("&#328;" , "n");
    	chaine=chaine.replaceAll("&#329;" , "n");

    	chaine=chaine.replaceAll("Ã’" , "O");
    	chaine=chaine.replaceAll("Ã“" , "O");
    	chaine=chaine.replaceAll("Ã”" , "O");
    	chaine=chaine.replaceAll("Ã•" , "O");
    	chaine=chaine.replaceAll("Ã–" , "O");
    	chaine=chaine.replaceAll("Ã˜" , "O");
    	chaine=chaine.replaceAll("&#332;" , "O");
    	chaine=chaine.replaceAll("&#334;" , "O");
    	chaine=chaine.replaceAll("&#336;" , "O");
    	chaine=chaine.replaceAll("&#416;" , "O");
    	chaine=chaine.replaceAll("&#465;" , "O");
    	chaine=chaine.replaceAll("&#510;" , "O");
    	chaine=chaine.replaceAll("&#7884;" , "O");
    	chaine=chaine.replaceAll("&#7886;" , "O");
    	chaine=chaine.replaceAll("&#7888;" , "O");
    	chaine=chaine.replaceAll("&#7890;" , "O");
    	chaine=chaine.replaceAll("&#7892;" , "O");
    	chaine=chaine.replaceAll("&#7894;" , "O");
    	chaine=chaine.replaceAll("&#7896;" , "O");
    	chaine=chaine.replaceAll("&#7898;" , "O");
    	chaine=chaine.replaceAll("&#7900;" , "O");
    	chaine=chaine.replaceAll("&#7902;" , "O");
    	chaine=chaine.replaceAll("&#7904;" , "O");
    	chaine=chaine.replaceAll("&#7906;" , "O");

    	chaine=chaine.replaceAll("Ã²" , "o");
    	chaine=chaine.replaceAll("Ã³" , "o");
    	chaine=chaine.replaceAll("Ã´" , "o");
    	chaine=chaine.replaceAll("Ãµ" , "o");
    	chaine=chaine.replaceAll("Ã¶" , "o");
    	chaine=chaine.replaceAll("Ã¸" , "o");
    	chaine=chaine.replaceAll("Ã°" , "o");
    	chaine=chaine.replaceAll("&#333;" , "o");
    	chaine=chaine.replaceAll("&#335;" , "o");
    	chaine=chaine.replaceAll("&#337;" , "o");
    	chaine=chaine.replaceAll("&#417;" , "o");
    	chaine=chaine.replaceAll("&#466;" , "o");
    	chaine=chaine.replaceAll("&#511;" , "o");
    	chaine=chaine.replaceAll("&#7885;" , "o");
    	chaine=chaine.replaceAll("&#7887;" , "o");
    	chaine=chaine.replaceAll("&#7889;" , "o");
    	chaine=chaine.replaceAll("&#7891;" , "o");
    	chaine=chaine.replaceAll("&#7893;" , "o");
    	chaine=chaine.replaceAll("&#7895;" , "o");
    	chaine=chaine.replaceAll("&#7897;" , "o");
    	chaine=chaine.replaceAll("&#7899;" , "o");
    	chaine=chaine.replaceAll("&#7901;" , "o");
    	chaine=chaine.replaceAll("&#7903;" , "o");
    	chaine=chaine.replaceAll("&#7905;" , "o");
    	chaine=chaine.replaceAll("&#7907;" , "o");

    	chaine=chaine.replaceAll("&#340;" , "R");
    	chaine=chaine.replaceAll("&#342;" , "R");
    	chaine=chaine.replaceAll("&#344;" , "R");

    	chaine=chaine.replaceAll("&#341;" , "r");
    	chaine=chaine.replaceAll("&#343;" , "r");
    	chaine=chaine.replaceAll("&#345;" , "r");

    	chaine=chaine.replaceAll("&#346;" , "S");
    	chaine=chaine.replaceAll("&#348;" , "S");
    	chaine=chaine.replaceAll("&#350;" , "S");
    	chaine=chaine.replaceAll("Å " , "S");

    	chaine=chaine.replaceAll("&#347;" , "s");
    	chaine=chaine.replaceAll("&#349;" , "s");
    	chaine=chaine.replaceAll("&#351;" , "s");
    	chaine=chaine.replaceAll("Å¡" , "s");

    	chaine=chaine.replaceAll("&#354;" , "T");
    	chaine=chaine.replaceAll("&#356;" , "T");
    	chaine=chaine.replaceAll("&#358;" , "T");

    	chaine=chaine.replaceAll("&#355;" , "t");
    	chaine=chaine.replaceAll("&#357;" , "t");
    	chaine=chaine.replaceAll("&#359;" , "t");

    	chaine=chaine.replaceAll("Ã™" , "U");
    	chaine=chaine.replaceAll("Ãš" , "U");
    	chaine=chaine.replaceAll("Ã›" , "U");
    	chaine=chaine.replaceAll("Ãœ" , "U");
    	chaine=chaine.replaceAll("&#360;" , "U");
    	chaine=chaine.replaceAll("&#362;" , "U");
    	chaine=chaine.replaceAll("&#364;" , "U");
    	chaine=chaine.replaceAll("&#366;" , "U");
    	chaine=chaine.replaceAll("&#368;" , "U");
    	chaine=chaine.replaceAll("&#370;" , "U");
    	chaine=chaine.replaceAll("&#431;" , "U");
    	chaine=chaine.replaceAll("&#467;" , "U");
    	chaine=chaine.replaceAll("&#469;" , "U");
    	chaine=chaine.replaceAll("&#471;" , "U");
    	chaine=chaine.replaceAll("&#473;" , "U");
    	chaine=chaine.replaceAll("&#475;" , "U");
    	chaine=chaine.replaceAll("&#7908;" , "U");
    	chaine=chaine.replaceAll("&#7910;" , "U");
    	chaine=chaine.replaceAll("&#7912;" , "U");
    	chaine=chaine.replaceAll("&#7914;" , "U");
    	chaine=chaine.replaceAll("&#7916;" , "U");
    	chaine=chaine.replaceAll("&#7918;" , "U");
    	chaine=chaine.replaceAll("&#7920;" , "U");

    	chaine=chaine.replaceAll("Ã¹" , "u");
    	chaine=chaine.replaceAll("Ãº" , "u");
    	chaine=chaine.replaceAll("Ã»" , "u");
    	chaine=chaine.replaceAll("Ã¼" , "u");
    	chaine=chaine.replaceAll("&#361;" , "u");
    	chaine=chaine.replaceAll("&#363;" , "u");
    	chaine=chaine.replaceAll("&#365;" , "u");
    	chaine=chaine.replaceAll("&#367;" , "u");
    	chaine=chaine.replaceAll("&#369;" , "u");
    	chaine=chaine.replaceAll("&#371;" , "u");
    	chaine=chaine.replaceAll("&#432;" , "u");
    	chaine=chaine.replaceAll("&#468;" , "u");
    	chaine=chaine.replaceAll("&#470;" , "u");
    	chaine=chaine.replaceAll("&#472;" , "u");
    	chaine=chaine.replaceAll("&#474;" , "u");
    	chaine=chaine.replaceAll("&#476;" , "u");
    	chaine=chaine.replaceAll("&#7909;" , "u");
    	chaine=chaine.replaceAll("&#7911;" , "u");
    	chaine=chaine.replaceAll("&#7913;" , "u");
    	chaine=chaine.replaceAll("&#7915;" , "u");
    	chaine=chaine.replaceAll("&#7917;" , "u");
    	chaine=chaine.replaceAll("&#7919;" , "u");
    	chaine=chaine.replaceAll("&#7921;" , "u");

    	chaine=chaine.replaceAll("&#372;" , "W");
    	chaine=chaine.replaceAll("&#7808;" , "W");
    	chaine=chaine.replaceAll("&#7810;" , "W");
    	chaine=chaine.replaceAll("&#7812;" , "W");

    	chaine=chaine.replaceAll("&#373;" , "w");
    	chaine=chaine.replaceAll("&#7809;" , "w");
    	chaine=chaine.replaceAll("&#7811;" , "w");
    	chaine=chaine.replaceAll("&#7813;" , "w");

    	chaine=chaine.replaceAll("Ã�" , "Y");
    	chaine=chaine.replaceAll("&#374;" , "Y");
    	chaine=chaine.replaceAll("Å¸" , "Y");
    	chaine=chaine.replaceAll("&#7922;" , "Y");
    	chaine=chaine.replaceAll("&#7928;" , "Y");
    	chaine=chaine.replaceAll("&#7926;" , "Y");
    	chaine=chaine.replaceAll("&#7924;" , "Y");

    	chaine=chaine.replaceAll("Ã½" , "y");
    	chaine=chaine.replaceAll("Ã¿" , "y");
    	chaine=chaine.replaceAll("&#375;" , "y");
    	chaine=chaine.replaceAll("&#7929;" , "y");
    	chaine=chaine.replaceAll("&#7925;" , "y");
    	chaine=chaine.replaceAll("&#7927;" , "y");
    	chaine=chaine.replaceAll("&#7923;" , "y");

    	chaine=chaine.replaceAll("&#377;" , "Z");
    	chaine=chaine.replaceAll("&#379;" , "Z");
    	chaine=chaine.replaceAll("Å½" , "Z");

    	chaine=chaine.replaceAll("&#378;" , "z");
    	chaine=chaine.replaceAll("&#380;" , "z");
    	chaine=chaine.replaceAll("Å¾" , "z");

    	return chaine;
    }

}
