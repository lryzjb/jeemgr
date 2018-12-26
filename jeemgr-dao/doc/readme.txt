1.mybatis generator自动生成后操作：
model替换regex
匹配串：^(( |\t)+)this.(.*) = .* == null.*
替换串：$1this.$3 = $3 == null || $3.equals("") ? null : $3;
