package _2023.backtrack;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class WordSearch2Trie {
    public static char a = 'a';

    class Trie {
        public boolean isWord;
        public Trie[] children = new Trie[26]; // since all lowercase, else use Map<>

        public void addWord(String word) {
            Trie curr = this;
            for (char c : word.toCharArray()) {
                if (curr.children[c - a] == null) curr.children[c - a] = new Trie();
                curr = curr.children[c - a];
            }
            curr.isWord = true;
        }
    }

    Trie root = new Trie();
    public boolean found = false;
    public Set<String> foundSet = new HashSet<>();


    // https://leetcode.com/problems/word-search-ii/
    public List<String> findWords(char[][] board, String[] words) {
        int maxWordLength = 0;
        for (String word : words) {
            maxWordLength = Math.max(word.length(), maxWordLength);
            root.addWord(word);
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                //System.out.println(String.format("------------- [%d, %d] -------------", i, j));
                dfs(board, i, j, new char[maxWordLength], 0, root);
            }
        }
        return new ArrayList<>(foundSet);
    }


    public void dfs(char[][] board, int row, int col, char[] candidate, int pos, Trie trie) {

        if (found) return;
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) return;
        if (pos >= candidate.length) return;
        if (board[row][col] == 0) return; // already visited

        char curr = board[row][col];
        candidate[pos] = curr;
        Trie child = trie.children[curr - a];
        if (child == null) return; // curr is not present on trie branch, meaning not part of any word

        if (child.isWord) {
            String word = new String(Arrays.copyOf(candidate, pos + 1)).trim();
            //System.out.println(">> found " + word);
            foundSet.add(word);
            // no return, else substring will short-circuit a longer string
            // at the same time, foundList needs to a Set, as a longer string may cause duplicate substrings to be enlisted
            // Set is safe, because it is given that all words are unique
        }

        //System.out.println(String.format("at: board[%d][%d]: %s", row, col, board[row][col]));

        board[row][col] = 0; // remove
        dfs(board, row - 1, col, candidate, pos + 1, child);
        dfs(board, row + 1, col, candidate, pos + 1, child);
        dfs(board, row, col - 1, candidate, pos + 1, child);
        dfs(board, row, col + 1, candidate, pos + 1, child);
        board[row][col] = curr; // add back, backtracking
    }

    // https://leetcode.com/problems/word-search/
    public static void main(String[] args) {

        //        String[][] strArr = {{"o", "a", "a", "n"}, {"e", "t", "a", "e"}, {"i", "h", "k", "r"}, {"i", "f", "l", "v"}};
        //        String[] words = {"oath", "pea", "eat", "rain"};

        //        String[][] strArr = {{"o", "a", "b", "n"}, {"o", "t", "a", "e"}, {"a", "h", "k", "r"}, {"a", "f", "l", "v"}};
        //        String[] words = {"oa", "oaa"};

        String[][] strArr = {{"o", "a", "a", "n"}, {"e", "t", "a", "e"}, {"i", "h", "k", "r"}, {"i", "f", "l", "v"}};
        String[] words = {"oath", "pea", "eat", "rain", "oathi", "oathk", "oathf", "oate", "oathii", "oathfi", "oathfii"};
        // expected = ["oath","oathk","oathf","oathfi","oathfii","oathi","oathii","oate","eat"]

        //        String[][] strArr = {{"m", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l"}, {"n", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a"}, {"o", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a"}, {"p", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a"}, {"q", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a"}, {"r", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a"}, {"s", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a"}, {"t", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a"}, {"u", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a"}, {"v", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a"}, {"w", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a"}, {"x", "y", "z", "a", "a", "a", "a", "a", "a", "a", "a", "a"}};
        //        String[] words = {"aaaaaaaaaa", "aaaaaaaaab", "aaaaaaaaac", "aaaaaaaaad", "aaaaaaaaae", "aaaaaaaaaf", "aaaaaaaaag", "aaaaaaaaah", "aaaaaaaaai", "aaaaaaaaaj", "aaaaaaaaak", "aaaaaaaaal", "aaaaaaaaam", "aaaaaaaaan", "aaaaaaaaao", "aaaaaaaaap", "aaaaaaaaaq", "aaaaaaaaar", "aaaaaaaaas", "aaaaaaaaat", "aaaaaaaaau", "aaaaaaaaav", "aaaaaaaaaw", "aaaaaaaaax", "aaaaaaaaay", "aaaaaaaaaz", "aaaaaaaaba", "aaaaaaaabb", "aaaaaaaabc", "aaaaaaaabd", "aaaaaaaabe", "aaaaaaaabf", "aaaaaaaabg", "aaaaaaaabh", "aaaaaaaabi", "aaaaaaaabj", "aaaaaaaabk", "aaaaaaaabl", "aaaaaaaabm", "aaaaaaaabn", "aaaaaaaabo", "aaaaaaaabp", "aaaaaaaabq", "aaaaaaaabr", "aaaaaaaabs", "aaaaaaaabt", "aaaaaaaabu", "aaaaaaaabv", "aaaaaaaabw", "aaaaaaaabx", "aaaaaaaaby", "aaaaaaaabz", "aaaaaaaaca", "aaaaaaaacb", "aaaaaaaacc", "aaaaaaaacd", "aaaaaaaace", "aaaaaaaacf", "aaaaaaaacg", "aaaaaaaach", "aaaaaaaaci", "aaaaaaaacj", "aaaaaaaack", "aaaaaaaacl", "aaaaaaaacm", "aaaaaaaacn", "aaaaaaaaco", "aaaaaaaacp", "aaaaaaaacq", "aaaaaaaacr", "aaaaaaaacs", "aaaaaaaact", "aaaaaaaacu", "aaaaaaaacv", "aaaaaaaacw", "aaaaaaaacx", "aaaaaaaacy", "aaaaaaaacz", "aaaaaaaada", "aaaaaaaadb", "aaaaaaaadc", "aaaaaaaadd", "aaaaaaaade", "aaaaaaaadf", "aaaaaaaadg", "aaaaaaaadh", "aaaaaaaadi", "aaaaaaaadj", "aaaaaaaadk", "aaaaaaaadl", "aaaaaaaadm", "aaaaaaaadn", "aaaaaaaado", "aaaaaaaadp", "aaaaaaaadq", "aaaaaaaadr", "aaaaaaaads", "aaaaaaaadt", "aaaaaaaadu", "aaaaaaaadv", "aaaaaaaadw", "aaaaaaaadx", "aaaaaaaady", "aaaaaaaadz", "aaaaaaaaea", "aaaaaaaaeb", "aaaaaaaaec", "aaaaaaaaed", "aaaaaaaaee", "aaaaaaaaef", "aaaaaaaaeg", "aaaaaaaaeh", "aaaaaaaaei", "aaaaaaaaej", "aaaaaaaaek", "aaaaaaaael", "aaaaaaaaem", "aaaaaaaaen", "aaaaaaaaeo", "aaaaaaaaep", "aaaaaaaaeq", "aaaaaaaaer", "aaaaaaaaes", "aaaaaaaaet", "aaaaaaaaeu", "aaaaaaaaev", "aaaaaaaaew", "aaaaaaaaex", "aaaaaaaaey", "aaaaaaaaez", "aaaaaaaafa", "aaaaaaaafb", "aaaaaaaafc", "aaaaaaaafd", "aaaaaaaafe", "aaaaaaaaff", "aaaaaaaafg", "aaaaaaaafh", "aaaaaaaafi", "aaaaaaaafj", "aaaaaaaafk", "aaaaaaaafl", "aaaaaaaafm", "aaaaaaaafn", "aaaaaaaafo", "aaaaaaaafp", "aaaaaaaafq", "aaaaaaaafr", "aaaaaaaafs", "aaaaaaaaft", "aaaaaaaafu", "aaaaaaaafv", "aaaaaaaafw", "aaaaaaaafx", "aaaaaaaafy", "aaaaaaaafz", "aaaaaaaaga", "aaaaaaaagb", "aaaaaaaagc", "aaaaaaaagd", "aaaaaaaage", "aaaaaaaagf", "aaaaaaaagg", "aaaaaaaagh", "aaaaaaaagi", "aaaaaaaagj", "aaaaaaaagk", "aaaaaaaagl", "aaaaaaaagm", "aaaaaaaagn", "aaaaaaaago", "aaaaaaaagp", "aaaaaaaagq", "aaaaaaaagr", "aaaaaaaags", "aaaaaaaagt", "aaaaaaaagu", "aaaaaaaagv", "aaaaaaaagw", "aaaaaaaagx", "aaaaaaaagy", "aaaaaaaagz", "aaaaaaaaha", "aaaaaaaahb", "aaaaaaaahc", "aaaaaaaahd", "aaaaaaaahe", "aaaaaaaahf", "aaaaaaaahg", "aaaaaaaahh", "aaaaaaaahi", "aaaaaaaahj", "aaaaaaaahk", "aaaaaaaahl", "aaaaaaaahm", "aaaaaaaahn", "aaaaaaaaho", "aaaaaaaahp", "aaaaaaaahq", "aaaaaaaahr", "aaaaaaaahs", "aaaaaaaaht", "aaaaaaaahu", "aaaaaaaahv", "aaaaaaaahw", "aaaaaaaahx", "aaaaaaaahy", "aaaaaaaahz", "aaaaaaaaia", "aaaaaaaaib", "aaaaaaaaic", "aaaaaaaaid", "aaaaaaaaie", "aaaaaaaaif", "aaaaaaaaig", "aaaaaaaaih", "aaaaaaaaii", "aaaaaaaaij", "aaaaaaaaik", "aaaaaaaail", "aaaaaaaaim", "aaaaaaaain", "aaaaaaaaio", "aaaaaaaaip", "aaaaaaaaiq", "aaaaaaaair", "aaaaaaaais", "aaaaaaaait", "aaaaaaaaiu", "aaaaaaaaiv", "aaaaaaaaiw", "aaaaaaaaix", "aaaaaaaaiy", "aaaaaaaaiz", "aaaaaaaaja", "aaaaaaaajb", "aaaaaaaajc", "aaaaaaaajd", "aaaaaaaaje", "aaaaaaaajf", "aaaaaaaajg", "aaaaaaaajh", "aaaaaaaaji", "aaaaaaaajj", "aaaaaaaajk", "aaaaaaaajl", "aaaaaaaajm", "aaaaaaaajn", "aaaaaaaajo", "aaaaaaaajp", "aaaaaaaajq", "aaaaaaaajr", "aaaaaaaajs", "aaaaaaaajt", "aaaaaaaaju", "aaaaaaaajv", "aaaaaaaajw", "aaaaaaaajx", "aaaaaaaajy", "aaaaaaaajz", "aaaaaaaaka", "aaaaaaaakb", "aaaaaaaakc", "aaaaaaaakd", "aaaaaaaake", "aaaaaaaakf", "aaaaaaaakg", "aaaaaaaakh", "aaaaaaaaki", "aaaaaaaakj", "aaaaaaaakk", "aaaaaaaakl", "aaaaaaaakm", "aaaaaaaakn", "aaaaaaaako", "aaaaaaaakp", "aaaaaaaakq", "aaaaaaaakr", "aaaaaaaaks", "aaaaaaaakt", "aaaaaaaaku", "aaaaaaaakv", "aaaaaaaakw", "aaaaaaaakx", "aaaaaaaaky", "aaaaaaaakz", "aaaaaaaala", "aaaaaaaalb", "aaaaaaaalc", "aaaaaaaald", "aaaaaaaale", "aaaaaaaalf", "aaaaaaaalg", "aaaaaaaalh", "aaaaaaaali", "aaaaaaaalj", "aaaaaaaalk", "aaaaaaaall", "aaaaaaaalm", "aaaaaaaaln", "aaaaaaaalo", "aaaaaaaalp", "aaaaaaaalq", "aaaaaaaalr", "aaaaaaaals", "aaaaaaaalt", "aaaaaaaalu", "aaaaaaaalv", "aaaaaaaalw", "aaaaaaaalx", "aaaaaaaaly", "aaaaaaaalz", "aaaaaaaama", "aaaaaaaamb", "aaaaaaaamc", "aaaaaaaamd", "aaaaaaaame", "aaaaaaaamf", "aaaaaaaamg", "aaaaaaaamh", "aaaaaaaami", "aaaaaaaamj", "aaaaaaaamk", "aaaaaaaaml", "aaaaaaaamm", "aaaaaaaamn", "aaaaaaaamo", "aaaaaaaamp", "aaaaaaaamq", "aaaaaaaamr", "aaaaaaaams", "aaaaaaaamt", "aaaaaaaamu", "aaaaaaaamv", "aaaaaaaamw", "aaaaaaaamx", "aaaaaaaamy", "aaaaaaaamz", "aaaaaaaana", "aaaaaaaanb", "aaaaaaaanc", "aaaaaaaand", "aaaaaaaane", "aaaaaaaanf", "aaaaaaaang", "aaaaaaaanh", "aaaaaaaani", "aaaaaaaanj", "aaaaaaaank", "aaaaaaaanl", "aaaaaaaanm", "aaaaaaaann", "aaaaaaaano", "aaaaaaaanp", "aaaaaaaanq", "aaaaaaaanr", "aaaaaaaans", "aaaaaaaant", "aaaaaaaanu", "aaaaaaaanv", "aaaaaaaanw", "aaaaaaaanx", "aaaaaaaany", "aaaaaaaanz", "aaaaaaaaoa", "aaaaaaaaob", "aaaaaaaaoc", "aaaaaaaaod", "aaaaaaaaoe", "aaaaaaaaof", "aaaaaaaaog", "aaaaaaaaoh", "aaaaaaaaoi", "aaaaaaaaoj", "aaaaaaaaok", "aaaaaaaaol", "aaaaaaaaom", "aaaaaaaaon", "aaaaaaaaoo", "aaaaaaaaop", "aaaaaaaaoq", "aaaaaaaaor", "aaaaaaaaos", "aaaaaaaaot", "aaaaaaaaou", "aaaaaaaaov", "aaaaaaaaow", "aaaaaaaaox", "aaaaaaaaoy", "aaaaaaaaoz", "aaaaaaaapa", "aaaaaaaapb", "aaaaaaaapc", "aaaaaaaapd", "aaaaaaaape", "aaaaaaaapf", "aaaaaaaapg", "aaaaaaaaph", "aaaaaaaapi", "aaaaaaaapj", "aaaaaaaapk", "aaaaaaaapl", "aaaaaaaapm", "aaaaaaaapn", "aaaaaaaapo", "aaaaaaaapp", "aaaaaaaapq", "aaaaaaaapr", "aaaaaaaaps", "aaaaaaaapt", "aaaaaaaapu", "aaaaaaaapv", "aaaaaaaapw", "aaaaaaaapx", "aaaaaaaapy", "aaaaaaaapz", "aaaaaaaaqa", "aaaaaaaaqb", "aaaaaaaaqc", "aaaaaaaaqd", "aaaaaaaaqe", "aaaaaaaaqf", "aaaaaaaaqg", "aaaaaaaaqh", "aaaaaaaaqi", "aaaaaaaaqj", "aaaaaaaaqk", "aaaaaaaaql", "aaaaaaaaqm", "aaaaaaaaqn", "aaaaaaaaqo", "aaaaaaaaqp", "aaaaaaaaqq", "aaaaaaaaqr", "aaaaaaaaqs", "aaaaaaaaqt", "aaaaaaaaqu", "aaaaaaaaqv", "aaaaaaaaqw", "aaaaaaaaqx", "aaaaaaaaqy", "aaaaaaaaqz", "aaaaaaaara", "aaaaaaaarb", "aaaaaaaarc", "aaaaaaaard", "aaaaaaaare", "aaaaaaaarf", "aaaaaaaarg", "aaaaaaaarh", "aaaaaaaari", "aaaaaaaarj", "aaaaaaaark", "aaaaaaaarl", "aaaaaaaarm", "aaaaaaaarn", "aaaaaaaaro", "aaaaaaaarp", "aaaaaaaarq", "aaaaaaaarr", "aaaaaaaars", "aaaaaaaart", "aaaaaaaaru", "aaaaaaaarv", "aaaaaaaarw", "aaaaaaaarx", "aaaaaaaary", "aaaaaaaarz", "aaaaaaaasa", "aaaaaaaasb", "aaaaaaaasc", "aaaaaaaasd", "aaaaaaaase", "aaaaaaaasf", "aaaaaaaasg", "aaaaaaaash", "aaaaaaaasi", "aaaaaaaasj", "aaaaaaaask", "aaaaaaaasl", "aaaaaaaasm", "aaaaaaaasn", "aaaaaaaaso", "aaaaaaaasp", "aaaaaaaasq", "aaaaaaaasr", "aaaaaaaass", "aaaaaaaast", "aaaaaaaasu", "aaaaaaaasv", "aaaaaaaasw", "aaaaaaaasx", "aaaaaaaasy", "aaaaaaaasz", "aaaaaaaata", "aaaaaaaatb", "aaaaaaaatc", "aaaaaaaatd", "aaaaaaaate", "aaaaaaaatf", "aaaaaaaatg", "aaaaaaaath", "aaaaaaaati", "aaaaaaaatj", "aaaaaaaatk", "aaaaaaaatl", "aaaaaaaatm", "aaaaaaaatn", "aaaaaaaato", "aaaaaaaatp", "aaaaaaaatq", "aaaaaaaatr", "aaaaaaaats", "aaaaaaaatt", "aaaaaaaatu", "aaaaaaaatv", "aaaaaaaatw", "aaaaaaaatx", "aaaaaaaaty", "aaaaaaaatz", "aaaaaaaaua", "aaaaaaaaub", "aaaaaaaauc", "aaaaaaaaud", "aaaaaaaaue", "aaaaaaaauf", "aaaaaaaaug", "aaaaaaaauh", "aaaaaaaaui", "aaaaaaaauj", "aaaaaaaauk", "aaaaaaaaul", "aaaaaaaaum", "aaaaaaaaun", "aaaaaaaauo", "aaaaaaaaup", "aaaaaaaauq", "aaaaaaaaur", "aaaaaaaaus", "aaaaaaaaut", "aaaaaaaauu", "aaaaaaaauv", "aaaaaaaauw", "aaaaaaaaux", "aaaaaaaauy", "aaaaaaaauz", "aaaaaaaava", "aaaaaaaavb", "aaaaaaaavc", "aaaaaaaavd", "aaaaaaaave", "aaaaaaaavf", "aaaaaaaavg", "aaaaaaaavh", "aaaaaaaavi", "aaaaaaaavj", "aaaaaaaavk", "aaaaaaaavl", "aaaaaaaavm", "aaaaaaaavn", "aaaaaaaavo", "aaaaaaaavp", "aaaaaaaavq", "aaaaaaaavr", "aaaaaaaavs", "aaaaaaaavt", "aaaaaaaavu", "aaaaaaaavv", "aaaaaaaavw", "aaaaaaaavx", "aaaaaaaavy", "aaaaaaaavz", "aaaaaaaawa", "aaaaaaaawb", "aaaaaaaawc", "aaaaaaaawd", "aaaaaaaawe", "aaaaaaaawf", "aaaaaaaawg", "aaaaaaaawh", "aaaaaaaawi", "aaaaaaaawj", "aaaaaaaawk", "aaaaaaaawl", "aaaaaaaawm", "aaaaaaaawn", "aaaaaaaawo", "aaaaaaaawp", "aaaaaaaawq", "aaaaaaaawr", "aaaaaaaaws", "aaaaaaaawt", "aaaaaaaawu", "aaaaaaaawv", "aaaaaaaaww", "aaaaaaaawx", "aaaaaaaawy", "aaaaaaaawz", "aaaaaaaaxa", "aaaaaaaaxb", "aaaaaaaaxc", "aaaaaaaaxd", "aaaaaaaaxe", "aaaaaaaaxf", "aaaaaaaaxg", "aaaaaaaaxh", "aaaaaaaaxi", "aaaaaaaaxj", "aaaaaaaaxk", "aaaaaaaaxl", "aaaaaaaaxm", "aaaaaaaaxn", "aaaaaaaaxo", "aaaaaaaaxp", "aaaaaaaaxq", "aaaaaaaaxr", "aaaaaaaaxs", "aaaaaaaaxt", "aaaaaaaaxu", "aaaaaaaaxv", "aaaaaaaaxw", "aaaaaaaaxx", "aaaaaaaaxy", "aaaaaaaaxz", "aaaaaaaaya", "aaaaaaaayb", "aaaaaaaayc", "aaaaaaaayd", "aaaaaaaaye", "aaaaaaaayf", "aaaaaaaayg", "aaaaaaaayh", "aaaaaaaayi", "aaaaaaaayj", "aaaaaaaayk", "aaaaaaaayl", "aaaaaaaaym", "aaaaaaaayn", "aaaaaaaayo", "aaaaaaaayp", "aaaaaaaayq", "aaaaaaaayr", "aaaaaaaays", "aaaaaaaayt", "aaaaaaaayu", "aaaaaaaayv", "aaaaaaaayw", "aaaaaaaayx", "aaaaaaaayy", "aaaaaaaayz", "aaaaaaaaza", "aaaaaaaazb", "aaaaaaaazc", "aaaaaaaazd", "aaaaaaaaze", "aaaaaaaazf", "aaaaaaaazg", "aaaaaaaazh", "aaaaaaaazi", "aaaaaaaazj", "aaaaaaaazk", "aaaaaaaazl", "aaaaaaaazm", "aaaaaaaazn", "aaaaaaaazo", "aaaaaaaazp", "aaaaaaaazq", "aaaaaaaazr", "aaaaaaaazs", "aaaaaaaazt", "aaaaaaaazu", "aaaaaaaazv", "aaaaaaaazw", "aaaaaaaazx", "aaaaaaaazy", "aaaaaaaazz"};

        char[][] board = new char[strArr.length][strArr[0].length];

        for (int i = 0; i < strArr.length; i++) {
            System.out.println(Arrays.toString(strArr[i]));
            char[] chars = String.join("", strArr[i]).toCharArray();
            board[i] = chars;
        }
        System.out.println("word count " + words.length);
        System.out.println("\n\n");


        WordSearch2Trie wordSearch = new WordSearch2Trie();
        Instant start = Instant.now();
        wordSearch.findWords(board, words);

        long runTimeInMs = Duration.between(start, Instant.now()).toMillis();
        System.out.println("foundList size: " + wordSearch.foundSet.size());
        System.out.println("foundList : " + wordSearch.foundSet);
        System.out.println("runtime (ms) : " + runTimeInMs);


    }
}


/*
[A, B, C, E]
[S, F, C, S]
[A, D, E, E]



------------- [0, 0] -------------
------------- [0, 1] -------------
------------- [0, 2] -------------
------------- [0, 3] -------------
at: board[0][3]: E, char[0]: E
------------- [1, 0] -------------
------------- [1, 1] -------------
------------- [1, 2] -------------
------------- [1, 3] -------------
------------- [2, 0] -------------
------------- [2, 1] -------------
------------- [2, 2] -------------
at: board[2][2]: E, char[0]: E
at: board[2][3]: E, char[1]: E
------------- [2, 3] -------------
at: board[2][3]: E, char[0]: E
at: board[2][2]: E, char[1]: E
at: board[2][1]: D, char[2]: D
found? true
 */
