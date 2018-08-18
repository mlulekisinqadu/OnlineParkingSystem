package com.bryansinqadu.eparking_app;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChatIndexActivity extends ChatActivity {
    private DatabaseReference mChatIndicesRef;

    @Override
    protected FirebaseRecyclerAdapter<Chat, ChatHolder> newAdapter() {
        mChatIndicesRef = FirebaseDatabase.getInstance()
                .getReference()
                .child("ChatIndices")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        FirebaseRecyclerOptions<Chat> options =
                new FirebaseRecyclerOptions.Builder<Chat>()
                        .setIndexedQuery(
                                mChatIndicesRef.limitToFirst(100), sChatQuery.getRef(), Chat.class)
                        .setLifecycleOwner(this)
                        .build();

        return new FirebaseRecyclerAdapter<Chat, ChatHolder>(options) {
            @NonNull
            @Override
            public ChatHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new ChatHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.message, parent, false));
            }

            @Override
            public void onDataChanged() {
                mEmptyListMessage.setVisibility(getItemCount() == 0 ? View.VISIBLE : View.GONE);
            }

            @Override
            protected void onBindViewHolder(@NonNull ChatHolder holder, int position, @NonNull Chat model) {
                holder.bind(model);
            }
        };
    }

    @Override
    protected void onAddMessage(Chat chat) {
        DatabaseReference chatRef = sChatQuery.getRef().push();
        mChatIndicesRef.child(chatRef.getKey()).setValue(true);
        chatRef.setValue(chat);
    }
}
