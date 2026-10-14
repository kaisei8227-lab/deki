(function () {
    const currentPath = window.location.pathname;

    // ログインページ自体にいる場合はチェックをスキップ（無限リダイレクト防止）
    if (currentPath.endsWith('/login.html')) {
        return;
    }

    const storedUser = localStorage.getItem('user');

    // データが存在しない、または空文字の場合
    if (!storedUser || storedUser.trim() === '' || storedUser === 'null' || storedUser === 'undefined') {
        console.warn('未認証ユーザーのためログイン画面へ移動します');
        window.location.href = '/login.html';
        return;
    }

    // JSONとして正しくパースできるかチェック
    try {
        const user = JSON.parse(storedUser);
        if (!user || (!user.username && !user.id)) {
            throw new Error('Invalid user object');
        }
    } catch (e) {
        console.error('ユーザー情報の解析に失敗したため再ログインを要求します', e);
        localStorage.removeItem('user');
        window.location.href = '/login.html';
    }
})();